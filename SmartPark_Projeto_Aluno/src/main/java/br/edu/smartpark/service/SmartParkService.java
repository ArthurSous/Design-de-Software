package br.edu.smartpark.service;

import java.time.Duration;
import java.time.LocalDateTime;

import br.edu.smartpark.legacy.NotificationLegacyApi;
import br.edu.smartpark.model.ParkingSession;
import br.edu.smartpark.model.ParkingSpot;
import br.edu.smartpark.model.Vehicle;
import br.edu.smartpark.patterns.abstractfactory.ParkingInfrastructureFactory;
import br.edu.smartpark.patterns.observer.DriverObserver;
import br.edu.smartpark.patterns.observer.OperationsObserver;
import br.edu.smartpark.patterns.observer.ParkingPublisher;
import br.edu.smartpark.patterns.ports.GatePort;
import br.edu.smartpark.patterns.ports.PaymentPort;
import br.edu.smartpark.patterns.ports.SensorPort;
import br.edu.smartpark.patterns.strategy.PricingService;
import br.edu.smartpark.repository.InMemoryRepository;

public class SmartParkService {
    public final InMemoryRepository<ParkingSpot> spots=new InMemoryRepository<>();
    public final InMemoryRepository<Vehicle> vehicles=new InMemoryRepository<>();
    public final InMemoryRepository<ParkingSession> sessions=new InMemoryRepository<>();

    // O serviço agora depende apenas das PORTAS (interfaces), nunca das
    // classes legadas diretamente. Quem as fornece é o Abstract Factory.
    private final SensorPort sensor;
    private final PaymentPort payment;
    private final GatePort gate;
    private final NotificationLegacyApi notification=new NotificationLegacyApi();
    private final PricingService pricing=new PricingService();
    private final ParkingPublisher publisher=new ParkingPublisher();

    public SmartParkService(){
        this(new ParkingInfrastructureFactory());
    }

    public SmartParkService(ParkingInfrastructureFactory infrastructureFactory){
        this.sensor=infrastructureFactory.sensor("default");
        this.payment=infrastructureFactory.payment("default");
        this.gate=infrastructureFactory.gate("default");

        // Agora os dois observadores coexistem (ParkingPublisher suporta lista).
        publisher.subscribe(new DriverObserver());
        publisher.subscribe(new OperationsObserver());
    }

    public ParkingSpot reserve(String plate,String type){
        for(ParkingSpot spot:spots.all()){
            // Só reserva vaga livre e ainda não reservada, do tipo certo.
            if(type.equals(spot.type) && !spot.occupied && !spot.reserved){
                spot.reserved=true;
                spot.vehiclePlate=plate;
                publisher.publish(spot.id,"SPOT_RESERVED");
                return spot;
            }
        }
        System.out.println("RESERVE_FAILED plate="+plate+" type="+type+" motivo=nenhuma vaga livre/compativel");
        return null;
    }

    public ParkingSession enter(String sessionId,String plate,String spotId,String entryTime){
        Vehicle vehicle=vehicles.find(plate);
        ParkingSpot spot=spots.find(spotId);
        if(vehicle==null||spot==null)return null;

        String sensorState=sensor.read(spotId);
        System.out.println("SENSOR="+sensorState);

        // Não abrimos mais a cancela se o sensor ou a vaga indicarem conflito.
        boolean spotAlreadyOccupied = spot.occupied;
        boolean sensorSaysOccupied = sensorState.contains("|OCCUPIED|");
        if(spotAlreadyOccupied || sensorSaysOccupied){
            System.out.println("ENTRY_DENIED spot="+spotId+" motivo=vaga ja ocupada");
            return null;
        }

        gate.open("ENTRY");
        spot.occupied=true;
        spot.vehiclePlate=plate;
        ParkingSession session=new ParkingSession(sessionId,plate,spotId,entryTime);
        sessions.save(sessionId,session);
        publisher.publish(sessionId,"VEHICLE_ENTERED");
        return session;
    }

    public void exit(String sessionId,String exitTime,String paymentMethod){
        ParkingSession session=sessions.find(sessionId);
        if(session==null)return;
        Vehicle vehicle=vehicles.find(session.vehiclePlate);
        ParkingSpot spot=spots.find(session.spotId);

        session.exitTime=exitTime;

        // Duração agora é calculada de verdade a partir dos horários de entrada e saída.
        long minutes=calculateMinutes(session.entryTime, session.exitTime);
        session.amount=pricing.calculate(vehicle==null?"CAR":vehicle.type,minutes);

        boolean approved=payment.charge(session.vehiclePlate,session.amount,paymentMethod);
        session.status=approved?"PAID":"PAYMENT_ERROR";

        if(approved){
            // A cancela de saída só abre se o pagamento foi aprovado.
            gate.open("EXIT");
            if(spot!=null){
                // Libera a vaga por completo: ocupação, reserva e placa associada.
                spot.occupied=false;
                spot.reserved=false;
                spot.vehiclePlate=null;
            }
        } else {
            System.out.println("EXIT_DENIED session="+sessionId+" motivo=pagamento nao aprovado");
        }

        notification.send(session.vehiclePlate,"Saída registrada. Valor="+session.amount+" status="+session.status);
        publisher.publish(sessionId,"VEHICLE_EXITED");
    }

    /** Calcula a duração real em minutos a partir de timestamps ISO-8601 (ex.: 2026-08-10T19:00:00). */
    private long calculateMinutes(String entryTime, String exitTime){
        try{
            LocalDateTime entry=LocalDateTime.parse(entryTime);
            LocalDateTime exit=LocalDateTime.parse(exitTime);
            long minutes=Duration.between(entry, exit).toMinutes();
            return Math.max(minutes, 0);
        }catch(Exception e){
            System.out.println("DURATION_PARSE_ERROR entry="+entryTime+" exit="+exitTime+" -> usando 0 minutos");
            return 0;
        }
    }

    public void syncSpot(String spotId){
        ParkingSpot spot=spots.find(spotId); if(spot==null)return;
        String state=sensor.read(spotId);
        spot.occupied=state.contains("|OCCUPIED|");
    }
}
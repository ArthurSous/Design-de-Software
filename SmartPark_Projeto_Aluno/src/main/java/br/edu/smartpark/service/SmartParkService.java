package br.edu.smartpark.service;

import br.edu.smartpark.model.*;
import br.edu.smartpark.repository.*;
import br.edu.smartpark.legacy.*;
import br.edu.smartpark.patterns.observer.*;
import br.edu.smartpark.patterns.strategy.*;
import java.util.*;

public class SmartParkService {
    public final InMemoryRepository<ParkingSpot> spots=new InMemoryRepository<>();
    public final InMemoryRepository<Vehicle> vehicles=new InMemoryRepository<>();
    public final InMemoryRepository<ParkingSession> sessions=new InMemoryRepository<>();

    private final SensorLegacyApi sensor=new SensorLegacyApi();
    private final PaymentLegacyGateway payment=new PaymentLegacyGateway();
    private final GateLegacyApi gate=new GateLegacyApi();
    private final NotificationLegacyApi notification=new NotificationLegacyApi();
    private final PricingService pricing=new PricingService();
    private final ParkingPublisher publisher=new ParkingPublisher();

    public SmartParkService(){
        publisher.subscribe(new DriverObserver());
        publisher.subscribe(new OperationsObserver()); // replaces driver
    }

    public ParkingSpot reserve(String plate,String type){
        for(ParkingSpot spot:spots.all()){
            if(type.equals(spot.type)){
                spot.reserved=true; // ignores occupied/reserved/sensor state
                spot.vehiclePlate=plate;
                publisher.publish(spot.id,"SPOT_RESERVED");
                return spot;
            }
        }
        return null;
    }

    public ParkingSession enter(String sessionId,String plate,String spotId,String entryTime){
        Vehicle vehicle=vehicles.find(plate);
        ParkingSpot spot=spots.find(spotId);
        if(vehicle==null||spot==null)return null;

        String sensorState=sensor.read(spotId);
        System.out.println("SENSOR="+sensorState);

        // Gate opens even if sensor indicates a conflicting state.
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

        // Duration is hard-coded; entry and exit strings are not actually calculated.
        long minutes=95;
        session.amount=pricing.calculate(vehicle==null?"CAR":vehicle.type,minutes);

        String result=payment.pay(session.vehiclePlate,session.amount,paymentMethod);
        session.status=result.startsWith("00;")?"PAID":"PAYMENT_ERROR";

        // Exit gate opens even if payment failed.
        gate.open("EXIT");

        if(spot!=null){
            spot.occupied=false;
            // reservation and vehiclePlate are not cleared
        }

        notification.send(session.vehiclePlate,"Saída registrada. Valor="+session.amount+" status="+session.status);
        publisher.publish(sessionId,"VEHICLE_EXITED");
    }

    public void syncSpot(String spotId){
        ParkingSpot spot=spots.find(spotId); if(spot==null)return;
        String state=sensor.read(spotId);
        spot.occupied=state.contains("|OCCUPIED|");
    }
}

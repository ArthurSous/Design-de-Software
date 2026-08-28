package br.edu.smartpark;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import br.edu.smartpark.model.ParkingSpot;
import br.edu.smartpark.model.Vehicle;
import br.edu.smartpark.patterns.adapter.PaymentAdapter;
import br.edu.smartpark.patterns.adapter.SensorAdapter;
import br.edu.smartpark.patterns.facade.SmartParkFacade;
import br.edu.smartpark.patterns.factory.SpotFactory;
import br.edu.smartpark.service.SmartParkService;

public class Main {
    public static void main(String[] args){
        // Corrige acentuação no console (ex.: "Saída" aparecendo como "Sa?da"),
        // forçando a saída padrão para UTF-8 independentemente do locale do SO.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        SmartParkService service=new SmartParkService();

        // Agora a Factory (antes morta/nunca usada) é quem cria as vagas.
        service.spots.save("A01", SpotFactory.create("CAR","A01"));
        service.spots.save("M01", SpotFactory.create("MOTORCYCLE","M01"));
        service.vehicles.save("ABC1D23",new Vehicle("ABC1D23","CAR","Cliente Demo"));

        service.reserve("ABC1D23","CAR");
        service.enter("S1","ABC1D23","A01","2026-08-10T19:00:00");

        SmartParkFacade facade=new SmartParkFacade(service,new SensorAdapter(),new PaymentAdapter());
        facade.leave("S1","2026-08-10T20:35:00","PIX");

        ParkingSpot spot=facade.getService().spots.find("A01");
        System.out.println("STATUS="+facade.getService().sessions.find("S1").status);
        System.out.println("SPOT_RESERVED="+spot.reserved);
        System.out.println("SPOT_VEHICLE="+spot.vehiclePlate);
    }
}
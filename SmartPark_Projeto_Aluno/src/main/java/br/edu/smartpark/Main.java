package br.edu.smartpark;
import br.edu.smartpark.model.*;
import br.edu.smartpark.service.*;
import br.edu.smartpark.patterns.facade.*;
import br.edu.smartpark.patterns.adapter.*;

public class Main {
    public static void main(String[] args){
        SmartParkService service=new SmartParkService();

        service.spots.save("A01",new ParkingSpot("A01","CAR"));
        service.spots.save("M01",new ParkingSpot("M01","MOTORCYCLE"));
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

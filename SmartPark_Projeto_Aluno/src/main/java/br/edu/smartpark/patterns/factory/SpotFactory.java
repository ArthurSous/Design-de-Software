package br.edu.smartpark.patterns.factory;
import br.edu.smartpark.model.ParkingSpot;
public class SpotFactory {
    public static ParkingSpot create(String type,String id){
        return new ParkingSpot(id,type);
    }
}

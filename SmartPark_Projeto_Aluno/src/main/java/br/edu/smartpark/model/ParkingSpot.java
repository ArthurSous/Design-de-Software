package br.edu.smartpark.model;
public class ParkingSpot {
    public String id;
    public String type;
    public boolean occupied;
    public boolean reserved;
    public String vehiclePlate;
    public ParkingSpot(String id,String type){
        this.id=id;this.type=type;
    }
}

package br.edu.smartpark.model;
public class ParkingSession {
    public String id;
    public String vehiclePlate;
    public String spotId;
    public String entryTime;
    public String exitTime;
    public String status="OPEN";
    public double amount;
    public ParkingSession(String id,String vehiclePlate,String spotId,String entryTime){
        this.id=id;this.vehiclePlate=vehiclePlate;this.spotId=spotId;this.entryTime=entryTime;
    }
}

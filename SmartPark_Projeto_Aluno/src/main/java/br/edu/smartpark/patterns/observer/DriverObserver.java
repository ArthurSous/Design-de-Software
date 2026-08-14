package br.edu.smartpark.patterns.observer;
public class DriverObserver implements ParkingObserver {
    public void update(String ref,String event){System.out.println("DRIVER "+ref+" "+event);}
}

package br.edu.smartpark.patterns.observer;
public class OperationsObserver implements ParkingObserver {
    public void update(String ref,String event){System.out.println("OPS "+ref+" "+event);}
}

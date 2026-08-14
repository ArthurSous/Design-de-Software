package br.edu.smartpark.patterns.observer;
public class ParkingPublisher {
    private ParkingObserver observer;
    public void subscribe(ParkingObserver observer){this.observer=observer;}
    public void publish(String ref,String event){if(observer!=null)observer.update(ref,event);}
}

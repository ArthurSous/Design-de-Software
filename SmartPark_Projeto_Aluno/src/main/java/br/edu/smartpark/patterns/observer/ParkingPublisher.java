package br.edu.smartpark.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Publisher de verdade: mantém uma LISTA de observadores. Antes,
 * "subscribe" guardava um único observer, então o segundo cadastrado
 * silenciosamente substituía o primeiro. Agora todos são notificados.
 */
public class ParkingPublisher {
    private final List<ParkingObserver> observers = new ArrayList<>();

    public void subscribe(ParkingObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(ParkingObserver observer) {
        observers.remove(observer);
    }

    public void publish(String ref, String event) {
        for (ParkingObserver observer : observers) {
            observer.update(ref, event);
        }
    }
}
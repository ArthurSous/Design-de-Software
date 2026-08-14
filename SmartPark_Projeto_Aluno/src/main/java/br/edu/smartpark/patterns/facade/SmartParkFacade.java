package br.edu.smartpark.patterns.facade;
import br.edu.smartpark.service.SmartParkService;
import br.edu.smartpark.patterns.adapter.*;
public class SmartParkFacade {
    public final SmartParkService service;
    public final SensorAdapter sensor;
    public final PaymentAdapter payment;
    public SmartParkFacade(SmartParkService service,SensorAdapter sensor,PaymentAdapter payment){
        this.service=service;this.sensor=sensor;this.payment=payment;
    }
    public void leave(String sessionId,String exitTime,String paymentMethod){
        service.exit(sessionId,exitTime,paymentMethod);
    }
    public SmartParkService getService(){return service;}
}

package br.edu.smartpark.patterns.abstractfactory;
import br.edu.smartpark.legacy.*;
public class ParkingInfrastructureFactory {
    public Object sensor(String family){return new SensorLegacyApi();}
    public Object payment(String family){return new PaymentLegacyGateway();}
    public Object gate(String family){return new GateLegacyApi();}
}

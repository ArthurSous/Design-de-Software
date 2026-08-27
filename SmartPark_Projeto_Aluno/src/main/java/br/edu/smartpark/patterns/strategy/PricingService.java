package br.edu.smartpark.patterns.strategy;
public class PricingService {
    private PricingStrategy strategy;
    public void setStrategy(PricingStrategy strategy){this.strategy=strategy;}

    public double calculate(String vehicleType,long minutes){
        if("MOTORCYCLE".equals(vehicleType)) return 5.0 + (minutes/60)*2.0;
        if("CAR".equals(vehicleType)) return 10.0 + (minutes/60)*5.0;
        return strategy==null ? 8.0 : strategy.calculate(minutes);
    }
}

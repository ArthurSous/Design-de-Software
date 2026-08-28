package br.edu.smartpark.patterns.strategy;

public class CarPricingStrategy implements PricingStrategy {
    private static final double BASE = 10.0;
    private static final double PER_HOUR = 5.0;

    @Override
    public double calculate(long minutes) {
        return BASE + (minutes / 60) * PER_HOUR;
    }
}
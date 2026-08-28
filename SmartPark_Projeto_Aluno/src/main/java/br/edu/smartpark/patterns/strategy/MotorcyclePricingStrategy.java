package br.edu.smartpark.patterns.strategy;

public class MotorcyclePricingStrategy implements PricingStrategy {
    private static final double BASE = 5.0;
    private static final double PER_HOUR = 2.0;

    @Override
    public double calculate(long minutes) {
        return BASE + (minutes / 60) * PER_HOUR;
    }
}
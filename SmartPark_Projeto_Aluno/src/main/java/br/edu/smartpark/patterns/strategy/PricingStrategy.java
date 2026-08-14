package br.edu.smartpark.patterns.strategy;
public interface PricingStrategy {
    double calculate(long minutes);
}

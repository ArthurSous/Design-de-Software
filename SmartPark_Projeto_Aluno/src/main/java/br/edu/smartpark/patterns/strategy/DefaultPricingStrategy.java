package br.edu.smartpark.patterns.strategy;

/** Estratégia usada quando o tipo de veículo não tem uma tarifa específica cadastrada. */
public class DefaultPricingStrategy implements PricingStrategy {
    private static final double FLAT_RATE = 8.0;

    @Override
    public double calculate(long minutes) {
        return FLAT_RATE;
    }
}
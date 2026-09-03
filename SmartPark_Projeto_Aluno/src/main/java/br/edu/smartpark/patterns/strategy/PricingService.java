package br.edu.smartpark.patterns.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Strategy de verdade: o cálculo de preço depende de qual PricingStrategy
 * está registrada para o tipo de veículo, e não de um if/else fixo.
 * Isso permite adicionar um novo tipo de veículo (ex.: "TRUCK") sem
 * alterar esta classe — basta registrar uma nova estratégia.
 */
public final class PricingService {
    private final Map<String, PricingStrategy> strategies = new HashMap<>();
    private final PricingStrategy defaultStrategy;

    public PricingService() {
        this(new DefaultPricingStrategy());
    }

    public PricingService(PricingStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
        registerStrategy("CAR", new CarPricingStrategy());
        registerStrategy("MOTORCYCLE", new MotorcyclePricingStrategy());
    }

    public void registerStrategy(String vehicleType, PricingStrategy strategy) {
        strategies.put(vehicleType, strategy);
    }

    public double calculate(String vehicleType, long minutes) {
        PricingStrategy strategy = strategies.getOrDefault(vehicleType, defaultStrategy);
        return strategy.calculate(minutes);
    }
}
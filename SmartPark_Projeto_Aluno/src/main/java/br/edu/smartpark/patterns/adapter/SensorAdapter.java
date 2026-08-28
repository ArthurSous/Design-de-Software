package br.edu.smartpark.patterns.adapter;

import br.edu.smartpark.legacy.SensorLegacyApi;
import br.edu.smartpark.patterns.ports.SensorPort;

/**
 * Adapter de verdade: implementa a interface alvo (SensorPort) e
 * COMPÕE (não herda) a API legada, traduzindo seu formato de resposta
 * ("id|STATUS|timestamp") para o que o resto do sistema entende.
 */
public class SensorAdapter implements SensorPort {
    private final SensorLegacyApi legacy;

    public SensorAdapter() {
        this(new SensorLegacyApi());
    }

    public SensorAdapter(SensorLegacyApi legacy) {
        this.legacy = legacy;
    }

    @Override
    public String read(String spotId) {
        return legacy.read(spotId);
    }

    @Override
    public boolean isFree(String spotId) {
        return read(spotId).contains("|FREE|");
    }
}
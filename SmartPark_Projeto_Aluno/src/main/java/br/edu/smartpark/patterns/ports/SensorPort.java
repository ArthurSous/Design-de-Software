package br.edu.smartpark.patterns.ports;

/**
 * Interface alvo (target) que o resto do sistema conhece.
 * O SmartParkService e a Facade dependem SOMENTE desta interface,
 * nunca da classe legada SensorLegacyApi diretamente.
 */
public interface SensorPort {
    String read(String spotId);
    boolean isFree(String spotId);
}
package br.edu.smartpark.patterns.ports;

public interface PaymentPort {
    boolean charge(String plate, double amount, String method);
}
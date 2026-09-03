package br.edu.smartpark.patterns.adapter;

import br.edu.smartpark.legacy.PaymentLegacyGateway;
import br.edu.smartpark.patterns.ports.PaymentPort;

public class PaymentAdapter implements PaymentPort {
    private final PaymentLegacyGateway legacy;

    public PaymentAdapter() {
        this(new PaymentLegacyGateway());
    }

    public PaymentAdapter(PaymentLegacyGateway legacy) {
        this.legacy = legacy;
    }

    @Override
    public boolean charge(String plate, double amount, String method) {
        return legacy.pay(plate, amount, method).startsWith("00;");
    }

    public PaymentLegacyGateway legacy() {
        return legacy;
    }
}
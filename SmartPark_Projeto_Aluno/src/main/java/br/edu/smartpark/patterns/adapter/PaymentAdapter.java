package br.edu.smartpark.patterns.adapter;
import br.edu.smartpark.legacy.PaymentLegacyGateway;
public class PaymentAdapter {
    private final PaymentLegacyGateway legacy=new PaymentLegacyGateway();
    public boolean charge(String plate,double amount,String method){
        return legacy.pay(plate,amount,method).startsWith("00;");
    }
    public PaymentLegacyGateway legacy(){return legacy;}
}

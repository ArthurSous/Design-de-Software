package br.edu.smartpark.legacy;
public class PaymentLegacyGateway {
    public String pay(String plate,double amount,String method){
        return amount >= 0 ? "00;APPROVED;"+method : "99;ERROR";
    }
}

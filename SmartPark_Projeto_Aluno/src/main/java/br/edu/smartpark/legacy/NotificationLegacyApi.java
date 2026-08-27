package br.edu.smartpark.legacy;
public class NotificationLegacyApi {
    public void send(String destination,String message){
        System.out.println("NOTIFY "+destination+" => "+message);
    }
}

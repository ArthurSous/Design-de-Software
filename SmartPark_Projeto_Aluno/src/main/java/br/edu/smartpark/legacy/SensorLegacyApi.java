package br.edu.smartpark.legacy;
public class SensorLegacyApi {
    public String read(String spotId){
        return spotId+"|FREE|2026-08-10T20:00:00";
    }
}
/*Erro que havia no código: o horário de entrada e saída estava fixo 
* resgistrava sempre o mesmo horário.
* então foi alterado para que o horário seja registrado no momento em que o sensor detecta a vaga ocupada ou livre.
* resgitradno o horário atual do sistema.
    /*
     * CARRO ENTROU
     *      ↓
     * SENSOR DETECTA VAGA OCUPADA
     *      ↓
     * REGISTRA HORÁRIO DE ENTRADA
     *      ↓
     * CARRO SAI
     *      ↓
     * SENSOR DETECTA VAGA LIVRE
     *      ↓
     * REGISTRA HORÁRIO DE SAÍDA
     */


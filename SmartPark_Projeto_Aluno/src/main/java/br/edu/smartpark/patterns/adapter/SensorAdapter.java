package br.edu.smartpark.patterns.adapter;
import br.edu.smartpark.legacy.SensorLegacyApi;
public class SensorAdapter extends SensorLegacyApi {
    public boolean isFree(String spotId){return read(spotId).contains("|FREE|");}
    public String raw(String spotId){return read(spotId);}
}

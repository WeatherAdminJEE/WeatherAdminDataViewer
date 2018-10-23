package imt.org.web.weatheradmindataviewer.bean;

import com.google.gson.Gson;
import imt.org.web.commonmodel.entities.SensorEntity;
import utils.SensorUtils;


public class SensorBean {
    private int id;
    private String name;
    private float lattitude;
    private float longitude;
    private SensorState state;
    // peut être les alertes

    String toJson(){
        return new Gson().toJson(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public SensorState getState() {
        return state;
    }

    public void setState(SensorState state) {
        this.state = state;
    }


}

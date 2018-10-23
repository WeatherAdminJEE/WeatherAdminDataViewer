package imt.org.web.weatheradmindataviewer.bean;

import com.google.gson.Gson;
import imt.org.web.commonmodel.entities.SensorEntity;
import lombok.Getter;
import lombok.Setter;
import utils.SensorUtils;

@Getter
@Setter
public class SensorBean {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private SensorState state;
    // peut être les alertes

    String toJson(){
        return new Gson().toJson(this);
    }

}

package imt.org.web.weatheradmindataviewer.bean;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorBean {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String type;
    private SensorState status;
    // peut être les alertes

    String toJson(){
        return new Gson().toJson(this);
    }

}

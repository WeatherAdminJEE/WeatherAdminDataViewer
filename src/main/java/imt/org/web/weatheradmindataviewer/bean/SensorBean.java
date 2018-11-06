package imt.org.web.weatheradmindataviewer.bean;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SensorBean {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String type;
    private SensorState status;
    private int idSensorAlertParam;
    private double alertValue;
    private Timestamp alertRange;

    String toJson(){
        return new Gson().toJson(this);
    }

}

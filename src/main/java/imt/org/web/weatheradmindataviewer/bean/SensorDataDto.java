package imt.org.web.weatheradmindataviewer.bean;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorDataDto {

    private int id;
    //@TODO private String sensorName
    private double value;
    private String type;
    private String date;

    String toJson(){
        return new Gson().toJson(this);
    }

}

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
    private double value;
    private String date;
    private SensorBean sensor;

    String toJson(){
        return new Gson().toJson(this);
    }

}

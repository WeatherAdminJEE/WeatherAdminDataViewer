package imt.org.web.weatheradmindataviewer.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SensorAlertParamDto {
    private int id;
    private double value;
    private Timestamp range;
}

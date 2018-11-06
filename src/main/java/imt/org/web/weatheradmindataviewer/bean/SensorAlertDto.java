package imt.org.web.weatheradmindataviewer.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SensorAlertDto {
    private int id;
    private SensorBean sensor;
    private SensorAlertParamDto param;
    private Timestamp startDate;
    private Timestamp endDate;
}

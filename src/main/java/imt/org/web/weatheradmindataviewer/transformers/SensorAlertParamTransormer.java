package imt.org.web.weatheradmindataviewer.transformers;

import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorAlertParamDto;

public class SensorAlertParamTransormer {

    public static SensorAlertParamDto entityToDto (SensorAlertParamEntity entity){
        SensorAlertParamDto dto = new SensorAlertParamDto();
        dto.setId(entity.getIdSensorAlertParam());
        dto.setValue(entity.getAlertValue());
        dto.setRange(entity.getAlertRange());

        return dto;
    }

}

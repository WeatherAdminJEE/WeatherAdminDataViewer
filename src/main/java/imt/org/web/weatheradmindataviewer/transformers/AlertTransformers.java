package imt.org.web.weatheradmindataviewer.transformers;

import imt.org.web.commonmodel.entities.SensorAlertEntity;
import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorAlertDto;
import imt.org.web.weatheradmindataviewer.bean.SensorDataDto;

import java.util.ArrayList;
import java.util.Collection;

public class AlertTransformers {

    public static SensorAlertDto entityToDto(SensorAlertEntity entity){
        SensorAlertDto dto = new SensorAlertDto();
        dto.setId(entity.getIdSensorAlert());
        dto.setSensorName(entity.getSensor().getNameSensor());
        dto.setParam(SensorAlertParamTransormer.entityToDto(entity.getSensorAlertParam()));
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        return dto;
    }

    public static Collection<SensorAlertDto> entityToDto (Collection<SensorAlertEntity> lstEntity){
        Collection<SensorAlertDto> lstDto = new ArrayList<>();
        for(SensorAlertEntity entity : lstEntity){
            lstDto.add(AlertTransformers.entityToDto(entity));
        }
        return lstDto;
    }

}

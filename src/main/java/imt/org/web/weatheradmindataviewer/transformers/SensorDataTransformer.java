package imt.org.web.weatheradmindataviewer.transformers;

import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorDataDto;

import java.util.ArrayList;
import java.util.Collection;

public class SensorDataTransformer {

    public static SensorDataDto entityToDto (SensorDataEntity entity){
        SensorDataDto dto = new SensorDataDto();


        dto.setId(entity.getIdSensorData());
        dto.setValue(entity.getMeasureValue());
        dto.setType(entity.getIdMeasureType()+"");
        dto.setDate(entity.getDate().toString());

        return dto;
    }

    public static Collection<SensorDataDto> entityToDto (Collection<SensorDataEntity> lstEntity){
        Collection<SensorDataDto> lstDto = new ArrayList<>();
        for(SensorDataEntity entity : lstEntity){
            lstDto.add(SensorDataTransformer.entityToDto(entity));
        }
        return lstDto;
    }

}

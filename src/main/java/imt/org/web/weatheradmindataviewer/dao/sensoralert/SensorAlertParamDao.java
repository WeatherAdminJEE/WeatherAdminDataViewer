package imt.org.web.weatheradmindataviewer.dao.sensoralert;

import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
public class SensorAlertParamDao {

    private IEntityFacade crudEntityFacade;

    public SensorAlertParamEntity findById(int idSensor){
        SensorEntity sensorEntity = (SensorEntity)crudEntityFacade.read(SensorEntity.class, idSensor);
        return sensorEntity.getSensorAlertParam();
    }

    public void updateSensorAlertParam(int idSensor, double alertValue, Timestamp alertRange) {
        SensorEntity sensorEntity = (SensorEntity)crudEntityFacade.read(SensorEntity.class, idSensor);
        sensorEntity.getSensorAlertParam().setAlertValue(alertValue);
        sensorEntity.getSensorAlertParam().setAlertRange(alertRange);
        crudEntityFacade.update(sensorEntity);
    }
}

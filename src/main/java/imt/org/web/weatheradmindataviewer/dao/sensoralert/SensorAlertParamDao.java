package imt.org.web.weatheradmindataviewer.dao.sensoralert;

import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SensorAlertParamDao {
    private IEntityFacade crudEntityFacade;

//    public Collection findAll(){
//        return crudEntityFacade.customFinder("select sensorAlert from SensorAlertParamEntity sensorAlert", null);
//    }

    public SensorAlertParamEntity findById(int idSensor){
        SensorEntity sensorEntity = (SensorEntity)crudEntityFacade.read(SensorEntity.class, idSensor);
        return sensorEntity.getSensorAlertParam();
//        return (SensorAlertParamEntity)crudEntityFacade.read(SensorAlertParamEntity.class, idSensor);
    }

    public void updateSensorAlertParam(int idSensorAlertParam, double alertValue, Timestamp alertRange) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update SensorAlertParamEntity alert ");
        stringBuilder.append("set alert.alertValue = :alertValue, alert.alertRange = :alertRange ");
        stringBuilder.append("where idSensorAlertParam = :idSensorAlertParam");

        Map queryParameters = new HashMap<>();
        queryParameters.put("idSensorAlertParam", idSensorAlertParam);
        queryParameters.put("alertValue", alertValue);
        queryParameters.put("alertRange", alertRange);
        crudEntityFacade.update(stringBuilder.toString(), queryParameters);
    }
}

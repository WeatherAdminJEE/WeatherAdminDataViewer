package imt.org.web.weatheradmindataviewer.dao.sensoralert;

import imt.org.web.commonmodel.entities.SensorAlertEntity;
import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SensorAlertDao {
    private IEntityFacade crudEntityFacade;
    public Collection findAll(){
        return crudEntityFacade.customFinder("select sensorAlert from SensorAlertEntity sensorAlert", null);
    }

    public SensorAlertParamEntity findById(int idAlert){
        return (SensorAlertParamEntity)crudEntityFacade.read(SensorAlertEntity.class, idAlert);
    }

    public Collection findAllAlertByIdSensor (int idSensor){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select alert from SensorAlertEntity alert");
        stringBuilder.append(" where ");
        stringBuilder.append("alert.sensor.idSensor = :idSensor");

        Map<String, Integer> queryParameters = new HashMap<>();
        queryParameters.put("idSensor", idSensor);
        return crudEntityFacade.customFinder(stringBuilder.toString(), queryParameters);
    }

}

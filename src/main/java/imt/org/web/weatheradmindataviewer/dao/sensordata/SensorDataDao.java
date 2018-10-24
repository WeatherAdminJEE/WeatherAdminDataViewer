package imt.org.web.weatheradmindataviewer.dao.sensordata;

import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SensorDataDao {

    private IEntityFacade crudEntityFacade;

    public Collection findAll() {

        return crudEntityFacade.customFinder("select sensorData from SensorData sensorData", null);
    }

    public Collection findAllDataBySensor(int idSensor) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select sensorData from SensorDataEntity sensorData, SensorEntity sensor");
        stringBuilder.append(" where ");
        stringBuilder.append("sensor.idSensor = :idSensor");
        stringBuilder.append(" and sensorData.idSensor = sensor.idSensor");

        Map<String, Integer> queryParameters = new HashMap<>();
        queryParameters.put("idSensor", idSensor);
        return crudEntityFacade.customFinder(stringBuilder.toString(), queryParameters);
    }

    public SensorDataEntity findLastDataBySensor (int idSensor){
        StringBuilder sb = new StringBuilder();
        sb.append("select sensorData from SensorDataEntity sensorData, SensorEntity sensor");
        sb.append(" where ");
        sb.append("sensor.idSensor = :idSensor");
        sb.append(" and sensorData.idSensor = sensor.idSensor");
        sb.append(" order by sensorData.date desc");
        Map<String, Integer> queryParameters = new HashMap<>();
        queryParameters.put("idSensor", idSensor);
        return (SensorDataEntity)crudEntityFacade.customFinder(sb.toString(), queryParameters).iterator().next();

    }
}

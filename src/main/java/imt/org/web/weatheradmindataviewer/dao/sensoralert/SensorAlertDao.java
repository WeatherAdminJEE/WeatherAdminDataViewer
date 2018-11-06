package imt.org.web.weatheradmindataviewer.dao.sensoralert;

import imt.org.web.commonmodel.entities.SensorAlertParamEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class SensorAlertDao {
    private IEntityFacade crudEntityFacade;

    public Collection findAll(){
        return crudEntityFacade.customFinder("select sensorAlert from SensorAlertParamEntity sensorAlert", null);
    }

    public SensorAlertParamEntity findById(int idSensor){
        return (SensorAlertParamEntity)crudEntityFacade.read(SensorAlertParamEntity.class, idSensor);
    }
}

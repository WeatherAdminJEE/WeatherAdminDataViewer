package imt.org.web.weatheradmindataviewer.dao.sensor;

import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SensorDao {

    private IEntityFacade crudEntityFacade;

    public Collection findAll() {

        return crudEntityFacade.customFinder("select sensor from SensorEntity sensor", null);
    }

    public SensorEntity findById(int idSensor){

        return (SensorEntity)crudEntityFacade.read(SensorEntity.class, idSensor);
    }


}

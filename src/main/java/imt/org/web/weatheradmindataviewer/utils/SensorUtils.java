package imt.org.web.weatheradmindataviewer.utils;

import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorState;
import imt.org.web.weatheradmindataviewer.crud.CRUDEntityFacade;
import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;
import imt.org.web.weatheradmindataviewer.dao.sensordata.SensorDataDao;

import java.util.Random;

public class SensorUtils {

    public static SensorState computeSensorState (SensorEntity sensor){

        Random rand = new Random();

        int  n = rand.nextInt(3) + 1;

        switch (n){
            case 1:
                return SensorState.NORMAL;

            case 2:
                return SensorState.DECONNECTE;

            case 3:
                return SensorState.ALERTE;
        }
        return SensorState.NORMAL;
    }

    public static SensorState computeSensorState2(SensorEntity sensor){
    IEntityFacade facade = new CRUDEntityFacade();
    SensorDataDao dao = new SensorDataDao(facade);
    try {
        SensorDataEntity lastRecord = dao.findLastDataBySensor(sensor.getIdSensor());

        System.out.println("computeSensorState");
        System.out.println(System.currentTimeMillis() - lastRecord.getDate().getTime());

        if(System.currentTimeMillis() - lastRecord.getDate().getTime() > 60000){
            return SensorState.DECONNECTE;
        }
    } catch (Exception e){
        System.out.println("No record for sensor : "+sensor.getNameSensor());
    }


    return SensorState.NORMAL;

    }
}

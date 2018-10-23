package utils;

import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorState;

public class SensorUtils {

    public static SensorState computeSensorState (SensorEntity sensor){
        return SensorState.NORMAL;
    }
}

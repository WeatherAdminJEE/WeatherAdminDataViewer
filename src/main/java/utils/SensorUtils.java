package utils;

import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorState;

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
}

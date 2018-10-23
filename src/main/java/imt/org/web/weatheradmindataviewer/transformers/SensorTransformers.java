package imt.org.web.weatheradmindataviewer.transformers;

import imt.org.web.commonmodel.entities.SensorEntity;
import imt.org.web.weatheradmindataviewer.bean.SensorBean;
import utils.SensorUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Ne contient que des methodes statiques pour transformer des entity en bean et vice versa
 */
public class SensorTransformers {

    /**
     *
     * @param entity l'entité à convertir
     * @return le bean correspondant à l'entité
     */
    public static SensorBean entityToBean(SensorEntity entity){
        SensorBean bean = new SensorBean();
        bean.setId(entity.getIdSensor());
        bean.setName(entity.getNameSensor());
        bean.setLatitude(Float.parseFloat(entity.getGpsCoordinates().split(",")[0]));
        bean.setLongitude(Float.parseFloat(entity.getGpsCoordinates().split(",")[1]));
        bean.setStatus(SensorUtils.computeSensorState(entity));

        return bean;
    }

    /**
     *
     * @param listEntity la liste à convertir
     * @return la liste convertie
     */
    public static Collection<SensorBean> entityToBean(Collection<SensorEntity> listEntity){
        List<SensorBean> listBean = new ArrayList<>();
        for(SensorEntity entity: listEntity){
            listBean.add(entityToBean(entity));
        }

        return listBean;
    }
}

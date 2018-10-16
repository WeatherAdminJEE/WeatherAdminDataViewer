package dao;

import imt.org.web.commonmodel.entities.SensorDataEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


public class SensorDataDao extends AbstractDao {

    public static SensorDataDao getInstance() {
        return new SensorDataDao();
    }

    private SensorDataDao() {
    }


    public Collection<SensorDataEntity> findAll() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Collection<SensorDataEntity> lst = new ArrayList<>();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            StringBuilder sb = new StringBuilder();

            sb.append("Select sensorData from SensorData sensorData");

            Query query = entityManager.createQuery(sb.toString());
            lst = query.getResultList();

            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("ERROR FIND ALL SENSOR DATA");
            System.out.println(e);

        } finally {
            entityManager.close();
        }
        return lst;
    }

    public Collection<SensorDataEntity> findAllDataBySensor(int idSensor) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Collection<SensorDataEntity> lst = new ArrayList<>();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            StringBuilder sb = new StringBuilder();

            sb.append("Select sensorData from SensorDataEntity sensorData, SensorEntity sensor");
            //sb.append(" on sensorData.idSensor = sensor.idSensor");
            sb.append(" where ");
            sb.append("sensor.idSensor = :idSensor");
            sb.append(" and sensorData.idSensor = sensor.idSensor");



            Query query = entityManager.createQuery(sb.toString());
            query.setParameter("idSensor" , idSensor);

            lst = query.getResultList();

            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("findAllDataBySensor");
            System.out.println(e);

        } finally {
            entityManager.close();
        }
        return lst;
    }
}

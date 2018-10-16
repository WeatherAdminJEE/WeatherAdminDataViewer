package dao;

import imt.org.web.commonmodel.entities.SensorDataEntity;
import imt.org.web.commonmodel.entities.SensorEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

public class SensorDao extends AbstractDao{

    public static SensorDao getInstance(){
        return new SensorDao();
    }

    private SensorDao(){

    }

    public Collection<SensorEntity> findAll(){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = entityManager.createQuery("Select sensor from SensorEntity sensor");
        return query.getResultList();
    }

    public SensorEntity findById(int idSensor){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        SensorEntity entity = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entity = entityManager.find(SensorEntity.class, idSensor);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e);

        } finally {
            entityManager.close();
        }
        return entity;
    }





}

package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class AbstractDao {
    protected static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("WeatherDatabase");

    /*protected Query prepareQuery(){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return entityManager.createQuery("");
    }*/
}

package imt.org.web.weatheradmindataviewer.crud;

import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Generic CRUD facade implementation
 * @param <T> Entity type
 */
public class CRUDEntityFacade<T> implements IEntityFacade<T> {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("WeatherDatabase");

    /**
     * Insert object
     * @param entity Entity
     */
    @Override
    public void create(final T entity) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        manager.persist(entity);
        manager.flush();
        manager.close();
    }

    /**
     * Select object
     * @param entity Entity class
     * @param primaryKey PrK
     * @return Requested object
     */
    @Override
    public T read(final Class<T> entity, final Serializable primaryKey) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        T entities = manager.find(entity, primaryKey);
        manager.close();
        return entities;
    }

    /**
     * Update object
     * @param entity Entity
     */
    @Override
    public void update(final T entity) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        manager.merge(entity);
        manager.flush();
        manager.close();
    }

    /**
     * Delete object
     * @param entity Entity
     */
    @Override
    public void delete(final T entity) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        manager.remove(entity);
        manager.flush();
        manager.close();
    }

    /**
     * Find objects with custom query
     * @param queryString Query
     * @return Objects
     */
    @Override
    public Collection<T> customFinder(String queryString) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = manager.createQuery(queryString);
        Collection<T> entities = query.getResultList();
        manager.close();
        return entities;
    }
}

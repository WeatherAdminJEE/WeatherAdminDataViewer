package imt.org.web.weatheradmindataviewer.crud;

import imt.org.web.weatheradmindataviewer.crud.facade.IEntityFacade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            System.out.println("CRUD facade - create() - Begin transaction");

            manager.merge(entity);
            transaction.commit();
            System.out.println("CRUD facade - create() - Transaction success");
        } catch (PersistenceException hibernateEx) {
            System.out.println("CRUD facade - create() - Insert error - " + hibernateEx.getMessage());
            if (transaction != null) {
                transaction.rollback();
                System.out.println("CRUD facade - create() - Action rollback !\n" + hibernateEx.getMessage());
            }
        } finally {
            manager.close();
            System.out.println("CRUD facade - create() - EntityManager closed");
        }
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
        T entities = null;

        try {
            System.out.println("CRUD facade - read() - Begin read");
            entities = manager.find(entity, primaryKey);
            System.out.println("CRUD facade - read() - Read success");
        } catch (PersistenceException hibernateEx) {
            System.out.println("CRUD facade - read() - Read error - " + hibernateEx.getMessage());

        } finally {
            manager.close();
            System.out.println("CRUD facade - read() - EntityManager closed");
            return entities;
        }
    }

    /**
     * Update object
     * @param entity Entity
     */
    @Override
    public void update(final T entity) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            System.out.println("CRUD facade - update() - Begin transaction");

            manager.merge(entity);
            transaction.commit();
            System.out.println("CRUD facade - update() - Transaction success");
        } catch (PersistenceException hibernateEx) {
            System.out.println("CRUD facade - update() - Update error - " + hibernateEx.getMessage());
            if (transaction != null) {
                transaction.rollback();
                System.out.println("CRUD facade - update() - Action rollback !\n" + hibernateEx.getMessage());
            }
        } finally {
            manager.close();
            System.out.println("CRUD facade - update() - EntityManager closed");
        }
    }

    /**
     * Delete object
     * @param entity Entity
     */
    @Override
    public void delete(final T entity) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            System.out.println("CRUD facade - delete() - Begin transaction");

            manager.remove(entity);
            transaction.commit();
            System.out.println("CRUD facade - delete() - Transaction success");
        } catch (PersistenceException hibernateEx) {
            System.out.println("CRUD facade - delete() - Delete error - " + hibernateEx.getMessage());
            if (transaction != null) {
                transaction.rollback();
                System.out.println("CRUD facade - delete() - Action rollback !\n" + hibernateEx.getMessage());
            }
        } finally {
            manager.close();
            System.out.println("CRUD facade - delete() - EntityManager closed");
        }

    }

    /**
     * Find objects with custom query
     * @param queryString Query
     * @return Objects
     */
    @Override
    public Collection customFinder(String queryString, Map<String, T> queryParameters) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        List entities = null;

        try {
            System.out.println("CRUD facade - customFinder() - Begin query");
            Query query = manager.createQuery(queryString);
            if(queryParameters != null && !queryParameters.isEmpty()) {
                for(Map.Entry<String, T> entry : queryParameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            entities = query.getResultList();
            System.out.println("CRUD facade - customFinder() - Query success");
        } catch (PersistenceException hibernateEx) {
            System.out.println("CRUD facade - customFinder() - Query error - " + hibernateEx.getMessage());
        } finally {
            manager.close();
            System.out.println("CRUD facade - customFinder() - EntityManager closed");
            return entities;
        }
    }
}

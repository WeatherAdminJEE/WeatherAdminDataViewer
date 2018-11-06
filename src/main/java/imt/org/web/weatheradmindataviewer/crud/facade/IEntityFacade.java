package imt.org.web.weatheradmindataviewer.crud.facade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Generic CRUD facade interface
 * @param <T> Entity type
 */
public interface IEntityFacade<T> {

    /**
     * Insert object
     * @param entity Entity
     */
    void create(T entity);

    /**
     * Select object
     * @param entity Entity class
     * @param primaryKey PrK
     * @return Requested object
     */
    T read(Class<T> entity, Serializable primaryKey);

    /**
     * Update object
     * @param queryString Query
     * @param queryParameters Query params
     */
    void update(String queryString, Map<String, T> queryParameters);

    /**
     * Delete object
     * @param entity Entity
     */
    void delete(T entity);

    /**
     * Find objects with custom query
     * @param queryString Query
     * @return Objects
     */
    <T> Collection<T> customFinder(String queryString, Map<String, T> queryParameters);
}

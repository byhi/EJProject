package com.byhi.ejproject.ejdata.service;
import java.util.List;

/**This interface is definition of basic e typical CRUD operations. Contain the implemetable typical CRUD operations.
 * @param <E> - entity
 */
public interface CrudService<E> {

    /**This method save entity
     * @param entity - entity for the save
     */
    void saveEntity(E entity) ;

    /**This method get entities
     * @return - entity list
     */
    List<E> getAllEntity() ;

    /**This method delete entity by id
     * @param id - id of entity
     */
    void deleteEntity(Long id);

    /**This method update entity by id
     * @param entity - entity  with new data
     * @param id - id of entity
     */
    void updateEntity(E entity, Long id);

    /**This method get entity by id
     * @param id - id of entity
     * @return - entity
     */
    E getEntityById(Long id);
}

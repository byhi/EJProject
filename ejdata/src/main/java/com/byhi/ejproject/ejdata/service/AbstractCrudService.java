package com.byhi.ejproject.ejdata.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**This abstraction is definition of basic CRUD services for CrudService implementations. Contain the typical CRUD operations.
 * @param <R> - repository type
 * @param <E> - entity type
 */
public abstract class AbstractCrudService<R extends CrudRepository<E, Long>, E> implements CrudService<E> {

    R repository;

    @Override
    public void saveEntity(E entity) {
        this.repository.save(entity);
    }

    @Override
    public List<E> getAllEntity() {
        return (List<E>) this.repository.findAll();
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<E> existEntity = this.repository.findById(id);
        if(existEntity.isPresent()) {
            this.repository.delete(existEntity.get());
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public abstract void updateEntity(E entity, Long id);

    @Override
    public E getEntityById(Long id) {
        Optional<E> entity = this.repository.findById(id);
        if(entity.isPresent()) {
            return  entity.get();
        }else {
            throw new NoSuchElementException();
        }
    }
}

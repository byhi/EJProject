package com.byhi.ejproject.ejdata.service;

import com.byhi.ejproject.ejdata.model.UserEntity;
import com.byhi.ejproject.ejdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This service extend AbstractCrudService and implement CrudService.
 */
@Service
public class UserService extends AbstractCrudService<UserRepository, UserEntity> {

    @Autowired
    public void setRepository( UserRepository repository){
        this.repository = repository;
    }

    /**This method implement abstraction, update entity by id
     * @param entity - entity  with new data
     * @param id     - id of entity
     * @throws NoSuchElementException - throw it if not found entity by id
     */
    @Override
    public void updateEntity(UserEntity entity, Long id) throws NoSuchElementException {
        Optional<UserEntity> existEntity = this.repository.findById(id);
            if(existEntity.isPresent()) {
                entity.setUserID(id);
                this.repository.save(entity);
            }else {
                throw new NoSuchElementException();
            }
    }

}

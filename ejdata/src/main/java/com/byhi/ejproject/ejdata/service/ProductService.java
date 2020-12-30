package com.byhi.ejproject.ejdata.service;

import com.byhi.ejproject.ejdata.model.ProductEntity;
import com.byhi.ejproject.ejdata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This service extend AbstractCrudService and implement CrudService.
 */
@Service
public class ProductService extends AbstractCrudService<ProductRepository, ProductEntity> implements CrudService<ProductEntity>  {

    @Autowired
    public void setRepository( ProductRepository repository){ this.repository = repository; }

    /**This method implement abstraction, update entity by id
     * @param entity - entity  with new data
     * @param id     - id of entity
     * @throws NoSuchElementException - throw it if not found entity by id
     */
    @Override
    public void updateEntity(ProductEntity entity, Long id) throws NoSuchElementException {
        Optional<ProductEntity> existEntity = this.repository.findById(id);
        if(existEntity.isPresent()) {
            entity.setProductID(id);
            this.repository.save(entity);
        }else {
            throw new NoSuchElementException();
        }
    }

}

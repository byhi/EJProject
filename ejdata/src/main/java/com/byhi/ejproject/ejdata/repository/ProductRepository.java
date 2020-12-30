package com.byhi.ejproject.ejdata.repository;

import com.byhi.ejproject.ejdata.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
}

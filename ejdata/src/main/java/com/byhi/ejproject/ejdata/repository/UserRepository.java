package com.byhi.ejproject.ejdata.repository;

import com.byhi.ejproject.ejdata.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
}

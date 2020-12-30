package com.byhi.ejproject.ejdata.controller;

import com.byhi.ejproject.ejdata.meter.FileLogging;
import com.byhi.ejproject.ejdata.model.UserEntity;
import com.byhi.ejproject.ejdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Users.
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<UserService , UserEntity> {

    @Autowired
    public void setFileLogging( FileLogging fileLogging){
       this.fileLogging = fileLogging;
    }

    @Autowired
    public void setUserService( UserService service){
        this.service = service;
    }

}

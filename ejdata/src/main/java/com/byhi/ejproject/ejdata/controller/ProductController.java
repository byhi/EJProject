package com.byhi.ejproject.ejdata.controller;

import com.byhi.ejproject.ejdata.meter.FileLogging;
import com.byhi.ejproject.ejdata.model.ProductEntity;
import com.byhi.ejproject.ejdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Products.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends AbstractCrudController<ProductService , ProductEntity> {

    @Autowired
    public void setFileLogging( FileLogging fileLogging){
        this.fileLogging = fileLogging;
    }

    @Autowired
    public void setUserService( ProductService service){
        this.service = service;
    }

}

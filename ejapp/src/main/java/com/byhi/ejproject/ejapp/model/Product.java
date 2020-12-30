package com.byhi.ejproject.ejapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    Long ProductID;

    Long ProductNumber;
    @JsonProperty
    private User userEntity;

    public Long getProductID() {
        return ProductID;
    }

    public void setProductID(Long productID) {
        ProductID = productID;
    }

    public Long getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(Long productNumber) {
        ProductNumber = productNumber;
    }

    public User getUser() {
        return userEntity;
    }

    public void setUser(User userEntity) {
        this.userEntity = userEntity;
    }
}

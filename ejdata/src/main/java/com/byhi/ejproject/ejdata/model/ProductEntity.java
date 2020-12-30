package com.byhi.ejproject.ejdata.model;

import javax.persistence.*;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ProductID;

    Long ProductNumber;


    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity userEntity;

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


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}

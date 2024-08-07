package com.example.BiddingSystem.model;

import jakarta.persistence.*;

@Entity(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "product_category_name")
    public String productCategoryName;
}

package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.model.ProductCategory;
import com.example.BiddingSystem.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductCategoryDao {

    private final ProductCategoryRepository productCategoryRepository;

    public Optional<ProductCategory> getProductCategoryById(Integer productCategoryId) {

        return productCategoryRepository.findById(productCategoryId);
    }
}

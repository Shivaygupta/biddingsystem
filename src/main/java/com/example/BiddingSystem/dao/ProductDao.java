package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.dto.request.CreateProductRequest;
import com.example.BiddingSystem.model.Product;
import com.example.BiddingSystem.model.ProductCategory;
import com.example.BiddingSystem.repository.ProductCategoryRepository;
import com.example.BiddingSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProductDao {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Integer createProduct(CreateProductRequest createProductRequest) {

        Product product = new Product();
        product.setProductCategoryId(createProductRequest.getProductCategoryId());
        product.setCreatedAt(Date.from(Instant.now()));
        product.setBasePrice(createProductRequest.getBasePrice());
        product.setProductName(createProductRequest.getProductName());
        product.setVendorId(createProductRequest.getVendorId());
        product.setUpdatedAt(Date.from(Instant.now()));

        Product productResponse = productRepository.save(product);
        return productResponse.getId();
    }

    public Optional<Product> getProductById(Integer productId) {

        return productRepository.findById(productId);
    }

    public Optional<ProductCategory> getProductCategoryByCategoryId(Integer productCategoryId) {

        return productCategoryRepository.findById(productCategoryId);
    }

    public List<Product> getProductByProductByCategoryId(Integer productCategoryId) {

        return productRepository.findByProductCategoryId(productCategoryId);
    }

}

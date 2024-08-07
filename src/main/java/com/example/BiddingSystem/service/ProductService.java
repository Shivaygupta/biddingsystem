package com.example.BiddingSystem.service;

import com.example.BiddingSystem.dto.request.CreateProductRequest;
import com.example.BiddingSystem.dto.response.ProductResponse;
import com.example.BiddingSystem.dto.response.ProductCategoryResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest createProductRequest);

    List<ProductResponse> findByProductCategoryId(Integer productCategoryId);
}

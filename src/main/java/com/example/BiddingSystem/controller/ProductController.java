package com.example.BiddingSystem.controller;

import com.example.BiddingSystem.dto.request.CreateProductRequest;
import com.example.BiddingSystem.dto.response.ProductResponse;
import com.example.BiddingSystem.dto.response.ProductCategoryResponse;
import com.example.BiddingSystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "v1/bidding/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createProduct")
    public ProductResponse createProduct(@RequestHeader("vendorToken") String vendorToken,
                                         @RequestBody @Valid CreateProductRequest createProductRequest) {

        log.info("createProduct API called with request: {}", createProductRequest);
        return productService.createProduct(createProductRequest);
    }

    @GetMapping("/getByProductCategoryId/{productCategoryId}")
    public List<ProductResponse> createProduct(@PathVariable("productCategoryId") Integer productCategoryId) {

        log.info("getByProductCategoryId API called with productCategoryId: {}", productCategoryId);
        return productService.findByProductCategoryId(productCategoryId);
    }
}

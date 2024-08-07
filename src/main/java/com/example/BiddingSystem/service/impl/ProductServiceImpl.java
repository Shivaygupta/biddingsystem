package com.example.BiddingSystem.service.impl;

import com.example.BiddingSystem.dao.ProductCategoryDao;
import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dao.VendorDao;
import com.example.BiddingSystem.dto.request.CreateProductRequest;
import com.example.BiddingSystem.dto.response.ProductResponse;
import com.example.BiddingSystem.dto.response.ProductCategoryResponse;
import com.example.BiddingSystem.exception.ProductException;
import com.example.BiddingSystem.exception.VendorException;
import com.example.BiddingSystem.model.Product;
import com.example.BiddingSystem.model.ProductCategory;
import com.example.BiddingSystem.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final VendorDao vendorDao;
    private final Double MIN_BASE_PRICE_VALUE = 0.0;
    private final String BASE_PRICE_CONSTANT = "Base price cannot be less than";

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        if (createProductRequest.getBasePrice() <= MIN_BASE_PRICE_VALUE) {
            throw new ProductException(HttpStatus.BAD_REQUEST.value(), BASE_PRICE_CONSTANT + MIN_BASE_PRICE_VALUE, HttpStatus.BAD_REQUEST.name());
        }

        vendorDao.findVendorById(createProductRequest.getVendorId())
                .orElseThrow(() -> {
                    log.error("Vendor with ID {} not found", createProductRequest.getVendorId());
                    return new VendorException(HttpStatus.NOT_FOUND.value(), "Vendor not found", HttpStatus.NOT_FOUND.name());
                });

        productCategoryDao.getProductCategoryById(createProductRequest.getProductCategoryId())
                .orElseThrow(() -> {
                    log.error("Product Category with ID {} not found", createProductRequest.getProductCategoryId());
                    return new ProductException(HttpStatus.NOT_FOUND.value(), "Product Category Not Found", HttpStatus.NOT_FOUND.name());
                });
        ProductResponse productResponse = createProductResponseFromRequest(createProductRequest);
        Integer productId = productDao.createProduct(createProductRequest);
        productResponse.setProductId(productId);
        return productResponse;
    }

    public List<ProductResponse> findByProductCategoryId(Integer productCategoryId) {
        List<Product> productList = productDao.getProductByProductByCategoryId(productCategoryId);
        if(productList.isEmpty()) {
            log.info("Product is Not present");
            return new ArrayList<>();
        }
        return createProductResponseListFromProduct(productList);

    }

    private ProductResponse createProductResponseFromRequest(CreateProductRequest createProductRequest) {
        return ProductResponse.builder()
                .productCategoryId(createProductRequest.getProductCategoryId())
                .productName(createProductRequest.getProductName())
                .vendorId(createProductRequest.getVendorId())
                .basePrice(createProductRequest.getBasePrice())
                .productCategoryId(createProductRequest.getProductCategoryId())
                .build();
    }

    private List<ProductResponse> createProductResponseListFromProduct(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (Product product : productList) {
            ProductResponse productResponse;
            productResponse = ProductResponse.builder()
                    .productCategoryId(product.getProductCategoryId())
                    .basePrice(product.getBasePrice())
                    .vendorId(product.getVendorId())
                    .productName(product.getProductName())
                    .productId(product.getId()).build();

            productResponseList.add(productResponse);
        }

        return productResponseList;
    }
}

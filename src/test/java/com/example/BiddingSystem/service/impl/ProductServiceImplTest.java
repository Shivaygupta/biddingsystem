package com.example.BiddingSystem.service.impl;

import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dto.request.CreateProductRequest;
import com.example.BiddingSystem.dto.response.ProductResponse;
import com.example.BiddingSystem.exception.ProductException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidProductCreation() {
        CreateProductRequest request = new CreateProductRequest();
        request.setBasePrice(100.0); // Assuming there's a setter for basePrice

        when(productDao.createProduct(any(CreateProductRequest.class))).thenReturn(1);

        ProductResponse response = productService.createProduct(request);

        assertNotNull(response);
        assertEquals(Integer.valueOf(1), response.getProductId());
        verify(productDao, times(1)).createProduct(request);
    }

    @Test(expected = ProductException.class)
    public void testProductCreationWithBasePriceZero() {
        CreateProductRequest request = new CreateProductRequest();
        request.setBasePrice(0.0); // Base price set to zero

        try {
            productService.createProduct(request);
        } catch (ProductException e) {
            // Check that the exception message and status are correct
            assertEquals("Base price cannot be less than0.0", e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST.name(), e.getErrorCode());
            throw e; // Rethrow to satisfy the expected exception
        }

        verify(productDao, never()).createProduct(request); // Ensure DAO is not called
    }
}

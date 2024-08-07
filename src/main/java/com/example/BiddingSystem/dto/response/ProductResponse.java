package com.example.BiddingSystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Integer productId;
    private Integer vendorId;
    private String productName;
    private Double basePrice;
    private Integer productCategoryId;
}

package com.example.BiddingSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    @NotNull(message = "vendorId can not be null")
    private Integer vendorId;
    @NotBlank(message = "product name can not be blank")
    private String productName;
    @NotNull(message = "basePrice can not be null")
    private Double basePrice;
    @NotNull(message = "productCategoryId can not be null")
    private Integer productCategoryId;
}

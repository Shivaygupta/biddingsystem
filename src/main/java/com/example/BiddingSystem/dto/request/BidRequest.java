package com.example.BiddingSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BidRequest {

    @NotNull(message = "productId can not be null")
    private Integer productId;
    @NotNull(message = "biddingPrice can not be null")
    private Double biddingPrice;
    @NotNull(message = "bidSlotId can not be null")
    private Integer bidSlotId;
    @NotNull(message = "bidderId can not be null")
    private Integer bidderId;
}

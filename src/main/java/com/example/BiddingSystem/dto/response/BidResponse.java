package com.example.BiddingSystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BidResponse {

    private Integer bidId;
    private Integer productId;
}

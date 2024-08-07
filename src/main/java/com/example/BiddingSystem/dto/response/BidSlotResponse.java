package com.example.BiddingSystem.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BidSlotResponse {

    private Integer bidSlotId;
    private Integer productId;
    private Date biddingStartTime;
    private Date biddingEndTime;
}

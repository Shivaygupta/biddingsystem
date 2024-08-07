package com.example.BiddingSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class BidSlotRequest {

    @NotNull(message = "biddingStartTime can not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date biddingStartTime;

    @NotNull(message = "biddingEndTime can not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date biddingEndTime;

    @NotNull(message = "bidLotSize can not be null")
    private Integer bidLotSize;

    @NotNull(message = "vendorId can not be null")
    private Integer vendorId;
}

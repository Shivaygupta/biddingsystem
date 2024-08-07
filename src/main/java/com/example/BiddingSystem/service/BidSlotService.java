package com.example.BiddingSystem.service;

import com.example.BiddingSystem.dto.request.BidSlotRequest;
import com.example.BiddingSystem.dto.response.BidSlotResponse;

public interface BidSlotService {

    BidSlotResponse createBidSlot(Integer productId, BidSlotRequest bidSlotRequest);
    BidSlotResponse getBidSlot(Integer productId, Integer bidSlotId);
}

package com.example.BiddingSystem.service;

import com.example.BiddingSystem.dto.request.BidRequest;
import com.example.BiddingSystem.dto.response.BidResponse;

public interface BidService {

    BidResponse placeBid(BidRequest bidRequest);

    Integer selectWinnerByProductIdAndBidSlotId(Integer productId, Integer bidSlotId);
}

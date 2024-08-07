package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.dto.request.BidSlotRequest;
import com.example.BiddingSystem.model.BidSlot;
import com.example.BiddingSystem.repository.BidSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BidSlotDao {

    private final BidSlotRepository bidSlotRepository;

    public Optional<BidSlot> getBidSlotById(Integer bidSlotId) {
        return bidSlotRepository.findById(bidSlotId);
    }

    public Integer createBidSlot(Integer productId, BidSlotRequest bidSlotRequest) {

        BidSlot bidSlot = new BidSlot();
        bidSlot.setSlotSize(bidSlotRequest.getBidLotSize());
        bidSlot.setBiddingStartTime(bidSlotRequest.getBiddingStartTime());
        bidSlot.setBiddingEndTime(bidSlotRequest.getBiddingEndTime());
        bidSlot.setVendorId(bidSlotRequest.getVendorId());
        bidSlot.setProductId(productId);
        BidSlot bidSlotResponse = bidSlotRepository.save(bidSlot);
        return bidSlotResponse.getId();
    }

}

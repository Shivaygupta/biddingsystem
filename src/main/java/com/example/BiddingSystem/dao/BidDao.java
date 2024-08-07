package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.dto.request.BidRequest;
import com.example.BiddingSystem.enums.Status;
import com.example.BiddingSystem.model.Bid;
import com.example.BiddingSystem.repository.BidRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class BidDao {

    private final BidRepository bidRepository;

    public Integer createBid(BidRequest bidRequest) {

        Bid bid = Bid.builder()
                .bidPrice(bidRequest.getBiddingPrice())
                .bidderId(bidRequest.getBidderId())
                .productId(bidRequest.getProductId())
                .bidSlotId(bidRequest.getBidSlotId())
                .status(Status.PENDING)
                .build();
        Bid response = bidRepository.save(bid);
        return response.getId();
    }

    public Optional<Integer> selectWinnerByProductIdAndBidSlotId(Integer productId, Integer bidSlotId) {

        return bidRepository.getBidderFromProductIdAndBidSlotId(productId, bidSlotId);
    }
}

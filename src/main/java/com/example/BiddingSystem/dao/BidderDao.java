package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.model.Bidder;
import com.example.BiddingSystem.repository.BidderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class BidderDao {

    private final BidderRepository bidderRepository;

    public Optional<Bidder> findBidderById(Integer bidderId) {

        return bidderRepository.findById(bidderId);
    }
}

package com.example.BiddingSystem.repository;

import com.example.BiddingSystem.model.BidSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BidSlotRepository extends JpaRepository<BidSlot, Integer> {

    @Query(value = "Select * from bid_slot where id = :bidSlotId and ")
    public Optional<BidSlot> getValidBidSlotById(Integer bidSlotId);
}

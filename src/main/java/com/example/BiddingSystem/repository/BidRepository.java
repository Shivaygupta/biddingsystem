package com.example.BiddingSystem.repository;

import com.example.BiddingSystem.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

    @Query(value = """
            SELECT bidder_id FROM bid 
            WHERE bid_price = (
                SELECT MAX(bid_price) 
                FROM bid 
                WHERE product_id = :productId 
                AND bid_slot_id = :bidSlotId 
                ORDER BY created_at DESC
            )
            AND product_id = :productId 
            AND bid_slot_id = :bidSlotId
            """, nativeQuery = true)
    Optional<Integer> getBidderFromProductIdAndBidSlotId(Integer productId, Integer bidSlotId);
}

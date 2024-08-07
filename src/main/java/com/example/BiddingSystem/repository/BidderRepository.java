package com.example.BiddingSystem.repository;

import com.example.BiddingSystem.model.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder, Integer> {
}

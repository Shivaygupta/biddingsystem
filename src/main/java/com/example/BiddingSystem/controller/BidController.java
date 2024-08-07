package com.example.BiddingSystem.controller;

import com.example.BiddingSystem.dto.request.BidRequest;
import com.example.BiddingSystem.dto.response.BidResponse;
import com.example.BiddingSystem.exception.BidderException;
import com.example.BiddingSystem.service.BidService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bidding/bids")
@AllArgsConstructor
@Slf4j
public class BidController {

    private final BidService bidService;

    @PostMapping("/createBid")
    public BidResponse placeBid(@RequestBody @Valid BidRequest bidRequest) {

        log.info("createBid API called with request {}", bidRequest);
        return bidService.placeBid(bidRequest);
    }
}

package com.example.BiddingSystem.controller;

import com.example.BiddingSystem.dto.request.BidSlotRequest;
import com.example.BiddingSystem.dto.response.BidSlotResponse;
import com.example.BiddingSystem.service.BidSlotService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/bidding")
@AllArgsConstructor
@Slf4j
public class BidSlotController {

    private final BidSlotService bidSlotService;

    @PostMapping("/slots/{productId}")
    public BidSlotResponse createBidSlot(@PathVariable("productId") Integer productId,
                                         @RequestBody @Valid BidSlotRequest bidSlotRequest) {

        log.info("createBidSlot API called with request: {} and productId {}", bidSlotRequest, productId);
        return bidSlotService.createBidSlot(productId, bidSlotRequest);
    }

    @GetMapping("/slots")
    public BidSlotResponse getBidSlot(@RequestParam("productId") Integer productId,
                                      @RequestParam("bidSlotId") Integer bidSlotId) {

        log.info("getBidSlot API called with productId: {} and bidSlotId {}", productId, bidSlotId);
        return bidSlotService.getBidSlot(productId, bidSlotId);
    }
}

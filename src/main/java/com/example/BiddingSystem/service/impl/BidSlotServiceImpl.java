package com.example.BiddingSystem.service.impl;

import com.example.BiddingSystem.dao.BidSlotDao;
import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dao.VendorDao;
import com.example.BiddingSystem.dto.request.BidSlotRequest;
import com.example.BiddingSystem.dto.response.BidSlotResponse;
import com.example.BiddingSystem.exception.BidSlotException;
import com.example.BiddingSystem.exception.ProductException;
import com.example.BiddingSystem.exception.VendorException;
import com.example.BiddingSystem.model.BidSlot;
import com.example.BiddingSystem.service.BidSlotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Service
@Slf4j
public class BidSlotServiceImpl implements BidSlotService {

    private final BidSlotDao bidSlotDao;
    private final ProductDao productDao;
    private final VendorDao vendorDao;

    public BidSlotResponse createBidSlot(Integer productId, BidSlotRequest bidSlotRequest) {

        productDao.getProductById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductException(HttpStatus.NOT_FOUND.value(), "Product not found", HttpStatus.NOT_FOUND.name());
                });

        vendorDao.findVendorById(bidSlotRequest.getVendorId())
                .orElseThrow(() -> {
                    log.error("Vendor with ID {} not found", bidSlotRequest.getVendorId());
                    return new VendorException(HttpStatus.NOT_FOUND.value(), "Vendor not found", HttpStatus.NOT_FOUND.name());
                });

        if (bidSlotRequest.getBiddingStartTime().after(bidSlotRequest.getBiddingEndTime())) {
            log.info("bidding slot duration is not valid {} - {}", bidSlotRequest.getBiddingStartTime(), bidSlotRequest.getBiddingEndTime());
            throw new BidSlotException(HttpStatus.NOT_FOUND.value(), "Bid Slot Duration is not valid", HttpStatus.NOT_FOUND.name());
        }
        Integer bidSlotId = bidSlotDao.createBidSlot(productId, bidSlotRequest);
        BidSlotResponse bidSlotResponse = createBidSlotResponse(productId, bidSlotRequest.getBiddingStartTime(), bidSlotRequest.getBiddingEndTime());
        bidSlotResponse.setBidSlotId(bidSlotId);
        return bidSlotResponse;
    }

    public BidSlotResponse getBidSlot(Integer productId, Integer bidSlotId) {

        productDao.getProductById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductException(HttpStatus.NOT_FOUND.value(), "Product not found", HttpStatus.NOT_FOUND.name());
                });

        BidSlot bidSlot = bidSlotDao.getBidSlotById(bidSlotId)
                .orElseThrow(() -> {
                    log.error("BidSlot with ID {} not found", bidSlotId);
                    throw new BidSlotException(HttpStatus.NOT_FOUND.value(), "BidSlot not found", HttpStatus.NOT_FOUND.name());
                });

        if (bidSlot.getBiddingEndTime().before(Date.from(Instant.now()))) {
            log.error("BidSlot with ID {} not found", bidSlotId);
            throw new BidSlotException(HttpStatus.NOT_FOUND.value(), "BidSlot not found", HttpStatus.NOT_FOUND.name());
        }
        BidSlotResponse bidSlotResponse = createBidSlotResponse(productId, bidSlot.getBiddingStartTime(), bidSlot.getBiddingStartTime());
        bidSlotResponse.setBidSlotId(bidSlotId);
        return bidSlotResponse;
    }


    public BidSlotResponse createBidSlotResponse(Integer productId, Date biddingStartTime, Date biddingEndTime) {

        return BidSlotResponse.builder()
                .biddingEndTime(biddingEndTime)
                .biddingStartTime(biddingStartTime)
                .productId(productId)
                .build();
    }
}

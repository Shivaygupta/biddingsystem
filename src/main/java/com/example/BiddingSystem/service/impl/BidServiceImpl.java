package com.example.BiddingSystem.service.impl;

import com.example.BiddingSystem.dao.BidDao;
import com.example.BiddingSystem.dao.BidSlotDao;
import com.example.BiddingSystem.dao.BidderDao;
import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dto.request.BidRequest;
import com.example.BiddingSystem.dto.response.BidResponse;
import com.example.BiddingSystem.exception.*;
import com.example.BiddingSystem.model.Product;
import com.example.BiddingSystem.service.BidService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BidServiceImpl implements BidService {

    private final ProductDao productDao;
    private final BidDao bidDao;
    private final BidderDao bidderDao;
    private final BidSlotDao bidSlotDao;

    public BidResponse placeBid(BidRequest bidRequest) throws BidderException {

        Product product = productDao.getProductById(bidRequest.getProductId())
                .orElseThrow(() -> new ProductException(HttpStatus.NOT_FOUND.value(), "Product not found", HttpStatus.NOT_FOUND.name()));

        bidSlotDao.getBidSlotById(bidRequest.getBidSlotId())
                .orElseThrow(() -> new BidSlotException(HttpStatus.NOT_FOUND.value(), "Bid Slot not found", HttpStatus.NOT_FOUND.name()));

        bidderDao.findBidderById(bidRequest.getBidderId())
                .orElseThrow(() -> new BidderException(HttpStatus.NOT_FOUND.value(), "Bidder not found", HttpStatus.NOT_FOUND.name()));

        if (bidRequest.getBiddingPrice().compareTo(product.getBasePrice()) < 0) {
            throw new BidException(ErrorCodes.BUSINESS_VALIDATION_ERROR.getCode(), "Bid must be equal to or greater than the base price", ErrorCodes.BUSINESS_VALIDATION_ERROR.name());
        }

        Integer bidId = bidDao.createBid(bidRequest);
        BidResponse bidResponse = createBidResponse(bidRequest);
        bidResponse.setBidId(bidId);
        return bidResponse;
    }

    public BidResponse createBidResponse(BidRequest bidRequest) {

        return BidResponse.builder().productId(bidRequest.getProductId()).build();
    }

    public Integer selectWinnerByProductIdAndBidSlotId(Integer productId, Integer bidSlotId) {

        Optional<Integer> winnerBidderIdOptional = bidDao.selectWinnerByProductIdAndBidSlotId(productId, bidSlotId);
        return winnerBidderIdOptional.orElseThrow(() -> new BidderException(HttpStatus.NOT_FOUND.value(), "Winner not found", HttpStatus.NOT_FOUND.name()));
    }
}

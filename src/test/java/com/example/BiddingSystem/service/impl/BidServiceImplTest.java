package com.example.BiddingSystem.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.example.BiddingSystem.dao.BidDao;
import com.example.BiddingSystem.dao.BidSlotDao;
import com.example.BiddingSystem.dao.BidderDao;
import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dto.request.BidRequest;
import com.example.BiddingSystem.dto.response.BidResponse;
import com.example.BiddingSystem.exception.CustomException;
import com.example.BiddingSystem.exception.BidderException;
import com.example.BiddingSystem.exception.BidSlotException;
import com.example.BiddingSystem.exception.ProductException;
import com.example.BiddingSystem.model.BidSlot;
import com.example.BiddingSystem.model.Bidder;
import com.example.BiddingSystem.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class BidServiceImplTest {
    @Mock
    private ProductDao productDao;
    @Mock
    private BidDao bidDao;
    @Mock
    private BidderDao bidderDao;
    @Mock
    private BidSlotDao bidSlotDao;
    @InjectMocks
    private BidServiceImpl bidService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ProductException.class)
    public void testPlaceBid_ProductNotFound() throws CustomException {
        BidRequest bidRequest = new BidRequest();
        bidRequest.setProductId(1);
        when(productDao.getProductById(1)).thenReturn(Optional.empty());
        bidService.placeBid(bidRequest);
    }

    @Test(expected = BidSlotException.class)
    public void testPlaceBid_BidSlotNotFound() throws CustomException {
        when(productDao.getProductById(1)).thenReturn(Optional.of(new Product()));
        BidRequest bidRequest = new BidRequest();
        bidRequest.setProductId(1);
        bidRequest.setBidSlotId(1);
        when(bidSlotDao.getBidSlotById(1)).thenReturn(Optional.empty());
        bidService.placeBid(bidRequest);
    }

    @Test(expected = BidderException.class)
    public void testPlaceBid_BidderNotFound() throws CustomException {
        when(productDao.getProductById(1)).thenReturn(Optional.of(new Product()));
        when(bidSlotDao.getBidSlotById(1)).thenReturn(Optional.of(new BidSlot()));
        BidRequest bidRequest = new BidRequest();
        bidRequest.setProductId(1);
        bidRequest.setBidSlotId(1);
        bidRequest.setBidderId(1);
        when(bidderDao.findBidderById(1)).thenReturn(Optional.empty());
        bidService.placeBid(bidRequest);
    }

    @Test(expected = CustomException.class)
    public void testPlaceBid_BiddingPriceBelowBasePrice() throws CustomException {
        Product product = new Product();
        product.setBasePrice(100.00);
        when(productDao.getProductById(1)).thenReturn(Optional.of(product));
        when(bidSlotDao.getBidSlotById(1)).thenReturn(Optional.of(new BidSlot()));
        when(bidderDao.findBidderById(1)).thenReturn(Optional.of(new Bidder()));
        BidRequest bidRequest = new BidRequest();
        bidRequest.setProductId(1);
        bidRequest.setBidSlotId(1);
        bidRequest.setBidderId(1);
        bidRequest.setBiddingPrice(50.00);
        bidService.placeBid(bidRequest);
    }

    @Test
    public void testPlaceBid_Successful() throws CustomException {

        Product product = new Product();
        product.setBasePrice(100.00);
        when(productDao.getProductById(1)).thenReturn(Optional.of(product));
        when(bidSlotDao.getBidSlotById(1)).thenReturn(Optional.of(new BidSlot()));
        when(bidderDao.findBidderById(1)).thenReturn(Optional.of(new Bidder()));
        BidRequest bidRequest = new BidRequest();
        bidRequest.setProductId(1);
        bidRequest.setBidSlotId(1);
        bidRequest.setBidderId(1);
        bidRequest.setBiddingPrice(150.00);
        when(bidDao.createBid(bidRequest)).thenReturn(1);
        BidResponse response = bidService.placeBid(bidRequest);
        assertNotNull(response);
        assertEquals(1, response.getBidId().intValue());
    }
}
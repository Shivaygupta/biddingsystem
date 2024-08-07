package com.example.BiddingSystem.service.impl;

import com.example.BiddingSystem.dao.BidSlotDao;
import com.example.BiddingSystem.dao.ProductDao;
import com.example.BiddingSystem.dao.VendorDao;
import com.example.BiddingSystem.dto.response.BidSlotResponse;
import com.example.BiddingSystem.exception.BidSlotException;
import com.example.BiddingSystem.exception.ProductException;
import com.example.BiddingSystem.exception.VendorException;
import com.example.BiddingSystem.model.Product;
import com.example.BiddingSystem.model.Vendor;
import com.example.BiddingSystem.service.BidSlotService;
import com.example.BiddingSystem.dto.request.BidSlotRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BidSlotServiceImplTest {

    private BidSlotService bidSlotService;
    private ProductDao productDao;
    private VendorDao vendorDao;
    private BidSlotDao bidSlotDao;

    @Before
    public void setUp() {
        productDao = mock(ProductDao.class);
        vendorDao = mock(VendorDao.class);
        bidSlotDao = mock(BidSlotDao.class);
        bidSlotService = new BidSlotServiceImpl(bidSlotDao, productDao, vendorDao);
    }

    @Test
    public void testCreateBidSlotValid() {
        Integer productId = 1;
        Integer vendorId = 1;
        Integer bidSlotId = 10;
        BidSlotRequest bidSlotRequest = BidSlotRequest.builder().vendorId(vendorId).biddingStartTime(new Date()).biddingEndTime(new Date(System.currentTimeMillis() + 10000)).build();  // valid time range

        when(productDao.getProductById(productId)).thenReturn(Optional.of(new Product()));
        when(vendorDao.findVendorById(vendorId)).thenReturn(Optional.of(new Vendor()));
        when(bidSlotDao.createBidSlot(productId, bidSlotRequest)).thenReturn(bidSlotId);

        BidSlotResponse response = bidSlotService.createBidSlot(productId, bidSlotRequest);

        assertNotNull(response);
        assertEquals(bidSlotId, response.getBidSlotId());
    }

    @Test(expected = ProductException.class)
    public void testCreateBidSlotProductNotFound() {
        Integer productId = 1;
        BidSlotRequest bidSlotRequest = BidSlotRequest.builder().biddingEndTime(new Date()).biddingStartTime(new Date()).build();  // valid time range

        when(productDao.getProductById(productId)).thenReturn(Optional.empty());

        bidSlotService.createBidSlot(productId, bidSlotRequest);
    }

    @Test(expected = VendorException.class)
    public void testCreateBidSlotVendorNotFound() {
        Integer productId = 1;
        Integer vendorId = 1;
        BidSlotRequest bidSlotRequest = BidSlotRequest.builder().vendorId(vendorId).biddingStartTime(new Date()).biddingEndTime(new Date()).build();  // valid time range

        when(productDao.getProductById(productId)).thenReturn(Optional.of(new Product()));
        when(vendorDao.findVendorById(vendorId)).thenReturn(Optional.empty());

        bidSlotService.createBidSlot(productId, bidSlotRequest);
    }

    @Test(expected = BidSlotException.class)
    public void testCreateBidSlotInvalidDuration() {
        Integer productId = 1;
        Integer vendorId = 1;
        // End time before start time
        BidSlotRequest bidSlotRequest = BidSlotRequest.builder().vendorId(vendorId).biddingStartTime(new Date()).biddingEndTime(new Date(System.currentTimeMillis() - 10000)).build();  // valid time range

        when(productDao.getProductById(productId)).thenReturn(Optional.of(new Product()));
        when(vendorDao.findVendorById(vendorId)).thenReturn(Optional.of(new Vendor()));

        bidSlotService.createBidSlot(productId, bidSlotRequest);
    }
}
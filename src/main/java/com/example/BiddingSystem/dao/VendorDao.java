package com.example.BiddingSystem.dao;

import com.example.BiddingSystem.model.Vendor;
import com.example.BiddingSystem.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class VendorDao {

    private final VendorRepository vendorRepository;

    public Optional<Vendor> findVendorById(Integer vendorId) {

        return vendorRepository.findById(vendorId);
    }
}

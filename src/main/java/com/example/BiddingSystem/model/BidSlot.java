package com.example.BiddingSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "bid_slot")
@Data
public class BidSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "slot_size")
    private Integer slotSize;

    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "product_id")
    private Integer productId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bidding_start_time")
    private Date biddingStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bidding_end_time")
    private Date biddingEndTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "bid_completed")
    private boolean bidCompleted;
}

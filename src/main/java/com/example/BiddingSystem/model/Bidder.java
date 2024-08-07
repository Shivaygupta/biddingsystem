package com.example.BiddingSystem.model;


import jakarta.persistence.*;

@Entity(name = "bidder")
public class Bidder extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}

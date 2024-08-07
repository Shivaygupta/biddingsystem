package com.example.BiddingSystem.model;

import jakarta.persistence.*;

@Entity(name = "vendor")
public class Vendor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String assetTag;

    private String assetName;

    @ManyToOne
    private Vendor vendor;

    private LocalDate purchaseDate;
    private Double purchaseCost;

    @ManyToOne
    private DepreciationRule depreciationRule;

    private String status;
    private LocalDateTime createdAt;

    public Asset() {}

    // getters & setters
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt;

    public AssetDisposal() {}

    // getters & setters
}

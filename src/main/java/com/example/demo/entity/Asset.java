package com.example.demo.entity;

public class Asset{
    @Id
    private long id;
    @Column(unique=true)
    private String assetTag;
    private String assetName;
    private LocalDate purchaseDate;
    @Min()
    private Double purchaseCost;
    private String status;
    private LocalDateTime createdAt;


    }
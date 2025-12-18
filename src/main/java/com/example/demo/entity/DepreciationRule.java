package com.example.demo.entity;

public class DeprecitionRule{
    @Id
    private long id;
    @Column(unique=true)
    private String vendorName;
    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt;

}
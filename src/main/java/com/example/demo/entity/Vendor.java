package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String vendorName;

    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt;

    public Vendor() {}

    public Vendor(String vendorName, String contactEmail, String phone) {
        this.vendorName = vendorName;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
}

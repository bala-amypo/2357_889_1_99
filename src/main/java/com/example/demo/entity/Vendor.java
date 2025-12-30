package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "vendorName")
)
public class Vendor extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String vendorName;

    @NotBlank
    @Column(nullable = false)
    private String contactEmail;
    
    
    
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}

package com.example.demo.dto;

import java.time.LocalDate;

public class AssetDisposalResponse {

    private Long id;
    private String disposalMethod;
    private double disposalValue;
    private LocalDate disposalDate;
    private String assetStatus;
    private String approvedBy;

    
    public Long getId() {
        return id;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public double getDisposalValue() {
        return disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public void setDisposalValue(double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public void setDisposalDate(LocalDate disposalDate) {
        this.disposalDate = disposalDate;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
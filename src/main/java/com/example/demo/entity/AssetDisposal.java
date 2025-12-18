package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String disposalMethod;

    @Min(0)
    private Double disposalValue;

    private LocalDate disposalDate;

    private LocalDateTime createdAt;

    
    public AssetDisposal() {
    }

    
    public AssetDisposal(long id, String disposalMethod, Double disposalValue,
                         LocalDate disposalDate, LocalDateTime createdAt) {
        this.id = id;
        this.disposalMethod = disposalMethod;
        this.disposalValue = disposalValue;
        this.disposalDate = disposalDate;
        this.createdAt = createdAt;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public void setDisposalDate(LocalDate disposalDate) {
        this.disposalDate = disposalDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

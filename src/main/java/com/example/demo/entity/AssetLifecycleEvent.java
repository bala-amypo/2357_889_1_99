package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class AssetLifecycleEvent extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String eventType;

    @NotBlank
    @Column(nullable = false)
    private String eventDescription;

    @NotNull
    @Column(nullable = false)
    private LocalDate eventDate;

    @NotNull
    @ManyToOne(optional = false)
    private Asset asset;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}

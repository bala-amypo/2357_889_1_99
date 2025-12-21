package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_lifecycle_events")
public class AssetLifecycleEvent {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDate eventDate;
    private LocalDateTime loggedAt;

    public AssetLifecycleEvent() {}

    // getters & setters
}

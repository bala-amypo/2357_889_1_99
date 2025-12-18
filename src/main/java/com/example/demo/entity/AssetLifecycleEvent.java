package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;
import jakarta.validation.constraints.Future;


@Entity
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String eventType;

    private String eventDescription;
    @Future
    private LocalDate eventDate;

    private LocalDateTime loggedAt;

    // default constructor
    public AssetLifecycleEvent() {
    }

    // parameterized constructor
    public AssetLifecycleEvent(long id, String eventType, String eventDescription,
                               LocalDate eventDate, LocalDateTime loggedAt) {
        this.id = id;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.loggedAt = loggedAt;
    }

    // getters & setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}

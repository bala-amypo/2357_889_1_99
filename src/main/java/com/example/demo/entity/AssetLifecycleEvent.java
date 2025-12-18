package com.example.demo.entity;
public class AssetLifecycleEvent{
    @Id
    private long id;
    private String eventType;
    private String eventDescription;
    private LocalDate evenDate;
    private LocalDateTime loggedAt;

}
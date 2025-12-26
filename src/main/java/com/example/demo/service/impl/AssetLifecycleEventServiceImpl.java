package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // Updated import
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepository, AssetRepository assetRepository) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        // FIX 1: 'eventType' is now an Enum (AssetStatus), so we only check for null.
        // We removed .trim().isEmpty() because Enums don't have those methods.
        if (event.getEventType() == null) {
            throw new IllegalArgumentException("Event type is required");
        }
        
        if (event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Event description must not be blank");
        }
        
        // FIX 2: Changed LocalDate.now() to LocalDateTime.now() to match the data type of getEventDate()
        if (event.getEventDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event date must not be in the future");
        }
        
        event.setAsset(asset);
        // This is optional since we added @PrePersist in the entity, but safe to keep.
        event.setLoggedAt(LocalDateTime.now());
        
        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        if (!assetRepository.existsById(assetId)) {
            throw new ResourceNotFoundException("Asset not found");
        }
        
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
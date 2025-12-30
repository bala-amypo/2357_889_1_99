package com.example.demo.controller;
import com.example.demo.dto.AssetLifecycleEventRequest;


import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventController(
            AssetLifecycleEventRepository eventRepository,
            AssetRepository assetRepository
    ) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }
    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> createEvent(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetLifecycleEventRequest request
    ) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (request.getEventDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Event date cannot be in the future");
        }

        AssetLifecycleEvent event = new AssetLifecycleEvent();
        event.setEventType(request.getEventType());
        event.setEventDescription(request.getEventDescription());
        event.setEventDate(request.getEventDate());
        event.setAsset(asset);

        AssetLifecycleEvent saved = eventRepository.save(event);
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getByAsset(
            @PathVariable Long assetId
    ) {
        assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        List<AssetLifecycleEvent> events =
                eventRepository.findByAssetIdOrderByEventDateDesc(assetId);

        return ResponseEntity.ok(events);
    }
}

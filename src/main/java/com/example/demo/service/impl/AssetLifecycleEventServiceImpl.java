package com.example.demo.service.impl;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {
    private final AssetLifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;
    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepo, AssetRepository assetRepo) {
        this.eventRepo = eventRepo; this.assetRepo = assetRepo;
    }
    @Override public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        var asset = assetRepo.findById(assetId).orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        if (event.getEventDescription() == null || event.getEventDescription().isBlank()) throw new IllegalArgumentException("Desc required");
        if (event.getEventDate().isAfter(LocalDate.now())) throw new IllegalArgumentException("Future date invalid");
        event.setAsset(asset);
        return eventRepo.save(event);
    }
    @Override public List<AssetLifecycleEvent> getEvents(Long assetId) { return eventRepo.findByAssetIdOrderByEventDateDesc(assetId); }
}

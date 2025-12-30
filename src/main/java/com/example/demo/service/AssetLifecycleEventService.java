package com.example.demo.service;

import com.example.demo.entity.AssetLifecycleEvent;

import java.util.List;

public interface AssetLifecycleEventService {
    AssetLifecycleEvent logEvent(AssetLifecycleEvent event);
    List<AssetLifecycleEvent> getEventsForAsset(Long assetId);
}

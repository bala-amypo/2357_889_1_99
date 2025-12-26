package com.example.demo.service;
import com.example.demo.entity.AssetLifecycleEvent;
import java.util.List;
public interface AssetLifecycleEventService {
    AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event);
    List<AssetLifecycleEvent> getEvents(Long assetId);
}

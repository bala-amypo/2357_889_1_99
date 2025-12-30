package com.example.demo.service.impl;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public AssetLifecycleEvent logEvent(AssetLifecycleEvent event) {

        if (event.getEventDate() == null) {
            throw new BadRequestException("Event date is required");
        }

        if (event.getEventDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Event date cannot be in the future");
        }

        if (event.getEventDescription() == null || event.getEventDescription().isBlank()) {
            throw new BadRequestException("Event description is required");
        }

        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}

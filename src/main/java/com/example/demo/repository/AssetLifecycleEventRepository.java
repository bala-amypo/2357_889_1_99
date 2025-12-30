package com.example.demo.repository;

import com.example.demo.entity.AssetLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
    List<AssetLifecycleEvent> findByAssetIdOrderByEventDateDesc(Long assetId);
}

package com.example.demo.repository;

import com.example.demo.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetLifecycleRepository extends JpaRepository<Asset, Long> {

}

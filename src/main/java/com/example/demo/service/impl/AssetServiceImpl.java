package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {
        if (asset.getPurchaseCost() < 0) {
            throw new BadRequestException("Purchase cost cannot be negative");
        }
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + id));
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new BadRequestException("Status cannot be empty");
        }
        return assetRepository.findByStatus(status);
    }
}

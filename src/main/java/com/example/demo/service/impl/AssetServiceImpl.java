package com.example.demo.service.impl;
import com.example.demo.entity.Asset;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepo;
    private final VendorRepository vendorRepo;
    private final DepreciationRuleRepository ruleRepo;
    public AssetServiceImpl(AssetRepository assetRepo, VendorRepository vendorRepo, DepreciationRuleRepository ruleRepo) {
        this.assetRepo = assetRepo; this.vendorRepo = vendorRepo; this.ruleRepo = ruleRepo;
    }
    @Override public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {
        var vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        var rule = ruleRepo.findById(ruleId).orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        if (asset.getPurchaseCost() <= 0) throw new IllegalArgumentException("Invalid cost");
        if (assetRepo.existsByAssetTag(asset.getAssetTag())) throw new IllegalArgumentException("Duplicate tag");
        asset.setVendor(vendor); asset.setDepreciationRule(rule);
        return assetRepo.save(asset);
    }
    @Override public List<Asset> getAllAssets() { return assetRepo.findAll(); }
    @Override public Asset getAsset(Long id) { return assetRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asset not found")); }
    @Override public List<Asset> getAssetsByStatus(String status) { return assetRepo.findByStatus(status); }
}

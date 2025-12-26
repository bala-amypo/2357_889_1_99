package com.example.demo.controller;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/assets")
public class AssetController {
    private final AssetService service;
    public AssetController(AssetService service) { this.service = service; }
    @PostMapping("/{vendorId}/{ruleId}") public ResponseEntity<Asset> create(@PathVariable Long vendorId, @PathVariable Long ruleId, @RequestBody Asset asset) {
        return ResponseEntity.ok(service.createAsset(vendorId, ruleId, asset));
    }
    @GetMapping public ResponseEntity<?> getAll() { return ResponseEntity.ok(service.getAllAssets()); }
    @GetMapping("/{id}") public ResponseEntity<?> getById(@PathVariable Long id) { return ResponseEntity.ok(service.getAsset(id)); }
    @GetMapping("/status/{status}") public ResponseEntity<?> getByStatus(@PathVariable String status) { return ResponseEntity.ok(service.getAssetsByStatus(status)); }
}

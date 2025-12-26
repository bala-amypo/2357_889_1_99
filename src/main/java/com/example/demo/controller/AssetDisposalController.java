package com.example.demo.controller;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/disposals")
public class AssetDisposalController {
    private final AssetDisposalService service;
    public AssetDisposalController(AssetDisposalService service) { this.service = service; }
    @PostMapping("/request/{assetId}") public ResponseEntity<AssetDisposal> request(@PathVariable Long assetId, @RequestBody AssetDisposal d) {
        return ResponseEntity.ok(service.requestDisposal(assetId, d));
    }
    @PutMapping("/approve/{disposalId}/{adminId}") public ResponseEntity<?> approve(@PathVariable Long disposalId, @PathVariable Long adminId) {
        return ResponseEntity.ok(service.approveDisposal(disposalId, adminId));
    }
}

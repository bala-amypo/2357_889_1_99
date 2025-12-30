
package com.example.demo.controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AssetDisposalResponse;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalController(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository,
            UserRepository userRepository
    ) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> request(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposal disposal
    ) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (disposal.getDisposalDate() == null) {
            throw new BadRequestException("Disposal date is required");
        }

        if (disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Disposal date cannot be in the future");
        }

        disposal.setAsset(asset);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(disposalRepository.save(disposal));
    }

        @Transactional
        @PutMapping("/approve/{disposalId}/{userId}")
        public ResponseEntity<AssetDisposalResponse> approve(
                @PathVariable Long disposalId,
                @PathVariable Long userId
        ) {
        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User approver = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        disposal.setApprovedBy(approver);

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");

        assetRepository.save(asset);
        disposalRepository.save(disposal);

        AssetDisposalResponse response = new AssetDisposalResponse();
        response.setId(disposal.getId());
        response.setDisposalMethod(disposal.getDisposalMethod());
        response.setDisposalValue(disposal.getDisposalValue());
        response.setDisposalDate(disposal.getDisposalDate());
        response.setAssetStatus(asset.getStatus());
        response.setApprovedBy(approver.getEmail());

        return ResponseEntity.ok(response);
        }

}

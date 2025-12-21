package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        return ResponseEntity.ok(createdVendor);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }
}
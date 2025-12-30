package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VendorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor vendor) {
        if (vendor.getVendorName() == null || vendor.getContactEmail() == null) {
            throw new IllegalArgumentException("Invalid vendor data");
        }
        return vendorRepository.save(vendor);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vendor getById(@PathVariable Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
    }
}

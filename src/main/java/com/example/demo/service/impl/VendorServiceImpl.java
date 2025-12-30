package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found with id: " + id));
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
    }
}

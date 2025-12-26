package com.example.demo.service.impl;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository repository;
    public VendorServiceImpl(VendorRepository repository) { this.repository = repository; }
    @Override public Vendor createVendor(Vendor vendor) {
        if (repository.findByVendorName(vendor.getVendorName()).isPresent()) throw new IllegalArgumentException("Vendor name exists");
        if (vendor.getContactEmail() == null || !vendor.getContactEmail().contains("@")) throw new IllegalArgumentException("Invalid email");
        return repository.save(vendor);
    }
    @Override public List<Vendor> getAllVendors() { return repository.findAll(); }
    @Override public Vendor getVendor(Long id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vendor not found")); }
}

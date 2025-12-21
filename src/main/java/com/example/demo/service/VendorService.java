package com.example.demo.service;

import com.example.demo.entity.Vendor;
import java.util.List;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    List<Vendor> getAllVendors();
}
package com.example.demo.controller;
import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/vendors")
public class VendorController {
    private final VendorService service;
    public VendorController(VendorService service) { this.service = service; }
    @PostMapping public ResponseEntity<Vendor> create(@RequestBody Vendor v) { return ResponseEntity.ok(service.createVendor(v)); }
    @GetMapping public ResponseEntity<?> getAll() { return ResponseEntity.ok(service.getAllVendors()); }
    @GetMapping("/{id}") public ResponseEntity<?> getById(@PathVariable Long id) { return ResponseEntity.ok(service.getVendor(id)); }
}

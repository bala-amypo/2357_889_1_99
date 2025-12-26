package com.example.demo.controller;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/rules")
public class DepreciationRuleController {
    private final DepreciationRuleService service;
    public DepreciationRuleController(DepreciationRuleService service) { this.service = service; }
    @PostMapping public ResponseEntity<DepreciationRule> create(@RequestBody DepreciationRule r) { return ResponseEntity.ok(service.createRule(r)); }
    @GetMapping public ResponseEntity<?> getAll() { return ResponseEntity.ok(service.getAllRules()); }
}

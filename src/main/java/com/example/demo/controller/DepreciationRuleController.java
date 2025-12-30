
package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleController(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @PostMapping
    public DepreciationRule create(@Valid @RequestBody DepreciationRule rule) {
        return ruleRepository.save(rule);
    }

    @GetMapping
    public List<DepreciationRule> getAll() {
        return ruleRepository.findAll();
    }
}

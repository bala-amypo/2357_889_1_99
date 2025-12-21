package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository depreciationRuleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository depreciationRuleRepository) {
        this.depreciationRuleRepository = depreciationRuleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life years must be greater than 0");
        }
        
        if (rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value must be greater than or equal to 0");
        }
        
        if (!"STRAIGHT_LINE".equals(rule.getMethod()) && !"DECLINING_BALANCE".equals(rule.getMethod())) {
            throw new IllegalArgumentException("Method must be STRAIGHT_LINE or DECLINING_BALANCE");
        }
        
        rule.setCreatedAt(LocalDateTime.now());
        return depreciationRuleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return depreciationRuleRepository.findAll();
    }
}
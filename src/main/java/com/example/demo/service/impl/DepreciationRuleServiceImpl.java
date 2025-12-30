package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0) {
            throw new BadRequestException("Useful life must be greater than zero");
        }
        if (rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value cannot be negative");
        }
        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public DepreciationRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Depreciation rule not found with id: " + id));
    }
}

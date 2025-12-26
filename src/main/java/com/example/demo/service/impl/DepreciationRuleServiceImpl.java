package com.example.demo.service.impl;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {
    private final DepreciationRuleRepository repository;
    public DepreciationRuleServiceImpl(DepreciationRuleRepository repository) { this.repository = repository; }
    @Override public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() <= 0) throw new IllegalArgumentException("Invalid life years");
        if (rule.getSalvageValue() < 0) throw new IllegalArgumentException("Invalid salvage value");
        if (!"STRAIGHT_LINE".equals(rule.getMethod()) && !"DECLINING_BALANCE".equals(rule.getMethod())) throw new IllegalArgumentException("Invalid method");
        return repository.save(rule);
    }
    @Override public List<DepreciationRule> getAllRules() { return repository.findAll(); }
}

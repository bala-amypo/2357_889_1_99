package com.example.demo.repository;

import com.example.demo.entity.DepreciationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepreciationRuleRepository extends JpaRepository<DepreciationRule, Long> {
    Optional<DepreciationRule> findByRuleName(String ruleName);
}
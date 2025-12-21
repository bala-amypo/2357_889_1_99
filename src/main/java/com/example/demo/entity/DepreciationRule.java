package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_rules")
public class DepreciationRule {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ruleName;

    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;

    public DepreciationRule() {}

    public DepreciationRule(String ruleName, String method, Integer usefulLifeYears, Double salvageValue) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
}

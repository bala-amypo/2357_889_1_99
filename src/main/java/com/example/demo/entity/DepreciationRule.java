package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
        name = "depreciation_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class DepreciationRule extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String ruleName;

    @NotBlank
    @Pattern(regexp = "STRAIGHT_LINE|DECLINING_BALANCE")
    @Column(nullable = false)
    private String method;

    @Positive
    @Column(nullable = false)
    private int usefulLifeYears;

    @PositiveOrZero
    @Column(nullable = false)
    private double salvageValue;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }
}

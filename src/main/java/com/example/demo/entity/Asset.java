package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(
        name = "assets",
        uniqueConstraints = @UniqueConstraint(columnNames = "assetTag")
)
public class Asset extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String assetTag;

    @NotBlank
    @Column(nullable = false)
    private String assetName;

    @NotNull
    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Positive
    @Column(nullable = false)
    private double purchaseCost;

    @NotBlank
    @Column(nullable = false)
    private String status = "ACTIVE";

    @ManyToOne(optional = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    private DepreciationRule depreciationRule;
    @OneToMany(mappedBy = "asset")
    @JsonIgnore
    private List<AssetDisposal> disposals;
        public String getAssetTag() {
            return assetTag;
        }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }
}

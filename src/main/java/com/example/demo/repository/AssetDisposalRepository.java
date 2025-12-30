package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetDisposalRepository
        extends JpaRepository<AssetDisposal, Long> {
  
    List<AssetDisposal> findByApprovedBy_Id(Long approvedById);

    default List<AssetDisposal> findByApprovedBy(User approvedBy) {
        if (approvedBy == null || approvedBy.getId() == null) {
            return List.of();
        }
        return findByApprovedBy_Id(approvedBy.getId());
    }
}

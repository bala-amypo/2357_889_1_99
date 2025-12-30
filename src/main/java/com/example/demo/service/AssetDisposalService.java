package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;

import java.util.List;

public interface AssetDisposalService {

    List<AssetDisposal> getDisposalsApprovedBy(Long userId);
}

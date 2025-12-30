package com.example.demo.service.impl;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(
            AssetDisposalRepository disposalRepository,
            UserRepository userRepository
    ) {
        this.disposalRepository = disposalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AssetDisposal> getDisposalsApprovedBy(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return disposalRepository.findByApprovedBy_Id(userId);
    }
}

package com.Test.service.impl;

import com.Test.dto.BranchDTO;
import com.Test.repository.BranchRepository;
import com.Test.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<BranchDTO> getBranchesByState(String state) {
        return branchRepository.findBranchesByState(state);
    }

    @Override
    public List<BranchDTO> getBranchesByCity(String city) {
        return branchRepository.findBranchesByCity(city);
    }

    @Override
    public List<BranchDTO> getBrancheAll() {
        return branchRepository.findBranches();
    }
}

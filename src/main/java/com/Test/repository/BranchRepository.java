package com.Test.repository;

import com.Test.dto.BranchDTO;
import java.util.List;

public interface BranchRepository {
    List<BranchDTO> findBranchesByState(String state);
    List<BranchDTO> findBranchesByCity(String city);
    List<BranchDTO> findBranches();
}
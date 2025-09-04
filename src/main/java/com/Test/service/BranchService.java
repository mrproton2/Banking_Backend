package com.Test.service;

import com.Test.dto.BranchDTO;
import java.util.List;

public interface BranchService {
    List<BranchDTO> getBranchesByState(String state);
    List<BranchDTO> getBranchesByCity(String city);
    List<BranchDTO> getBrancheAll();
}

package com.Test.controller;

import com.Test.dto.BranchDTO;
import com.Test.service.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:5173")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
//
//    // GET: http://localhost:8080/api/branches/state/Maharashtra
//    @GetMapping("/state/{state}")
//    public List<BranchDTO> getBranchesByState(@PathVariable String state) {
//        return branchService.getBranchesByState(state);
//    }

    // GET: http://localhost:8080/api/branches/Pune
    @GetMapping("/{city}")
    public List<BranchDTO> getBranchesByCity(@PathVariable String city) {
        return branchService.getBranchesByCity(city);
    }
    @GetMapping("/all")
    public List<BranchDTO> getBranchesAll() {
        return branchService.getBrancheAll();
    }
}

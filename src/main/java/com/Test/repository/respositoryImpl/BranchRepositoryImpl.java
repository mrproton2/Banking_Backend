package com.Test.repository.impl;

import com.Test.dto.BranchDTO;
import com.Test.repository.BranchRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    private final JdbcTemplate jdbcTemplate;

    public BranchRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<BranchDTO> branchMapper = (rs, rowNum) ->
            new BranchDTO(
                    rs.getInt("id"),
                    rs.getString("branch_name"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("pincode"),
                    rs.getString("branch_address")
            );

    @Override
    public List<BranchDTO> findBranchesByState(String state) {
        String sql = "SELECT branch_name, city, state, pincode, branch_address FROM branch WHERE state = ?";
        return jdbcTemplate.query(sql, branchMapper, state);
    }

    @Override
    public List<BranchDTO> findBranchesByCity(String city) {
        String sql = "SELECT branch_name, city, state, pincode, branch_address FROM branch WHERE city = ?";
        return jdbcTemplate.query(sql, branchMapper, city);
    }
    @Override
    public List<BranchDTO> findBranches() {
        String sql = "SELECT * FROM branch";
        return jdbcTemplate.query(sql, branchMapper);
    }
}

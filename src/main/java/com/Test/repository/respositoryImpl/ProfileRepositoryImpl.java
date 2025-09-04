package com.Test.repository.respositoryImpl;

import com.Test.dto.UserProfileDTO;
import com.Test.repository.ProfileRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserProfileDTO getUserProfileByEmail(String email) {
        String sql = """
            SELECT 
                u.name,
                u.mobile,
                u.email,
                u.address,
                u.accountType,
                a.account_no,
                b.City,
                b.State,
                b.branch_name,
                b.branch_address
            FROM users u
            JOIN accountdetails a ON u.id = a.user_id
            JOIN branch b ON u.branchId = b.id
            WHERE u.email = ?
        """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            UserProfileDTO dto = new UserProfileDTO();
            dto.setName(rs.getString("name"));
            dto.setMobile(rs.getString("mobile"));
            dto.setEmail(rs.getString("email"));
            dto.setAddress(rs.getString("address"));
            dto.setAccountType(rs.getString("accountType"));
            dto.setAccountNo(rs.getString("account_no"));
            dto.setCity(rs.getString("City"));
            dto.setState(rs.getString("State"));
            dto.setBranchName(rs.getString("branch_name"));
            dto.setBranchAddress(rs.getString("branch_address"));
            return dto;
        }, email);
    }
}
package com.Test.repository.respositoryImpl;
import com.Test.dto.AppUser;
import com.Test.dto.UserDTO;
import com.Test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveUser(UserDTO user) {
        String sql = "INSERT INTO users (name, mobile, email, address, aadhar_card, verified,accountType,branchId) VALUES (?,?,?,?,?,false,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getMobile(), user.getEmail(), user.getAddress(), user.getAadharCard(),user.getAccountType(),user.getBranchId());
    }


    @Override
    public void markVerified(String email,String passoword) {
        String sql = "UPDATE users SET password=?, verified=true WHERE email=?";
        jdbcTemplate.update(sql, passoword, email);
    }

    @Override
    public void accountDetails(int account_no,int branch_id,int user_id,String account_type) {
        String sql = "INSERT INTO accountdetails (account_no,branch_id, user_id, account_type) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, account_no, branch_id,user_id,account_type);
    }

    public int findUserIDByEmail(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        try {
            int id = jdbcTemplate.queryForObject(sql, Integer.class, email);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return Integer.parseInt("Data not found");
        }
    }



    @Override
    public Optional<Boolean> isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return Optional.of(count != null && count > 0);
    }

    public Optional<AppUser> findByEmail(String email) {
        String sql = "SELECT id, name, mobile, email, address, aadhar_card, verified, password, role FROM users WHERE email=?";
        try {
            AppUser u = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                AppUser x = new AppUser();
                x.setId(rs.getLong("id"));
                x.setName(rs.getString("name"));
                x.setMobile(rs.getString("mobile"));
                x.setEmail(rs.getString("email"));
                x.setAddress(rs.getString("address"));
                x.setAadharCard(rs.getString("aadhar_card"));
                x.setVerified(rs.getBoolean("verified"));
                x.setPassword(rs.getString("password"));
                x.setRole(rs.getString("role"));
                return x;
            }, email);
            return Optional.ofNullable(u);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

}

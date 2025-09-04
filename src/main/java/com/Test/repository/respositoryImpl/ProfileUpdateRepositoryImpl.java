package com.Test.repository.respositoryImpl;

import com.Test.repository.ProfileUpdateRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileUpdateRepositoryImpl implements ProfileUpdateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProfileUpdateRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getPasswordByEmail(String email) {
        String sql = "SELECT password FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, String.class, email);
    }

    @Override
    public int updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        return jdbcTemplate.update(sql, newPassword, email);
    }

    @Override
    public int updateProfile(String email, String name, String mobile, String address) {
        String sql = "UPDATE users SET name = ?, mobile = ?, address = ? WHERE email = ?";
        return jdbcTemplate.update(sql, name, mobile, address, email);
    }






}

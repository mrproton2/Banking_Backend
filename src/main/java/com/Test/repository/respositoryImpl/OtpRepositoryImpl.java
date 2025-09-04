package com.Test.repository.respositoryImpl;

import com.Test.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class OtpRepositoryImpl implements OtpRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveOtp(String email, String otp, LocalDateTime expiry) {
        String sql = "INSERT INTO otp_verification (email, otp, expiry_time) VALUES (?,?,?)";
        jdbcTemplate.update(sql, email, otp, Timestamp.valueOf(expiry));
    }

    @Override
    public Optional<String> getOtp(String email) {
        String sql = "SELECT otp FROM otp_verification WHERE email=? ORDER BY id DESC LIMIT 1";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, String.class, email));
    }

    @Override
    public Optional<LocalDateTime> getExpiry(String email) {
        String sql = "SELECT expiry_time FROM otp_verification WHERE email=? ORDER BY id DESC LIMIT 1";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, LocalDateTime.class, email));
    }
}

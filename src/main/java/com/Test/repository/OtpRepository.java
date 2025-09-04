package com.Test.repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository  {

    void saveOtp(String email, String otp, LocalDateTime expiry);
    Optional<String> getOtp(String email);
    Optional<LocalDateTime> getExpiry(String email);
}

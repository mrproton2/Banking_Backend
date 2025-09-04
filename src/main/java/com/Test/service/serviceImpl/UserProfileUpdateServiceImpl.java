package com.Test.service.serviceImpl;

import com.Test.dto.UpdateProfileRequest;
import com.Test.repository.ProfileUpdateRepository;
import com.Test.repository.UserRepository;
import com.Test.service.UserProfileUpdateService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UserProfileUpdateServiceImpl implements UserProfileUpdateService {

    private final ProfileUpdateRepository profileUpdateRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserProfileUpdateServiceImpl(ProfileUpdateRepository profileUpdateRepository, PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.profileUpdateRepository = profileUpdateRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository=userRepository;
    }

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword) {
        String dbPassword = profileUpdateRepository.getPasswordByEmail(email);

        if (!passwordEncoder.matches(oldPassword, dbPassword)) {
            return false; // old password mismatch
        }

        String encodedNew = passwordEncoder.encode(newPassword);
        int rows = profileUpdateRepository.updatePassword(email, encodedNew);
        return rows > 0;
    }

    @Override
    public boolean updateProfile(String email, UpdateProfileRequest request) {
        int rows = profileUpdateRepository.updateProfile(
                email,
                request.getName(),
                request.getMobile(),
                request.getAddress()
        );
        return rows > 0;
    }


    @Override
    public int getIDByEmail(String email) {
        int userId = Integer.parseInt(String.valueOf(userRepository.findUserIDByEmail(email)));
          return userId;
    }





}

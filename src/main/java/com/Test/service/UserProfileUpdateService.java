package com.Test.service;

import com.Test.dto.UpdateProfileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserProfileUpdateService {
    boolean resetPassword(String email, String oldPassword, String newPassword);
    boolean updateProfile(String email, UpdateProfileRequest request);
    int getIDByEmail(String email);


}

package com.Test.controller;

import com.Test.dto.ResetPasswordRequest;
import com.Test.dto.UpdateProfileRequest;
import com.Test.dto.UserProfileDTO;
import com.Test.jwtutils.JwtService;
import com.Test.service.ProfileService;
import com.Test.service.UserProfileUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    private final ProfileService profileService;
    private final JwtService jwtUtil;
    private final UserProfileUpdateService userProfileUpdateService;

    public ProfileController(ProfileService profileService, JwtService jwtUtil, UserProfileUpdateService userProfileUpdateService) {
        this.profileService = profileService;
        this.jwtUtil = jwtUtil;
        this.userProfileUpdateService = userProfileUpdateService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        UserProfileDTO profile = profileService.getProfile(email);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request,
                                                Authentication authentication) {
        String email = authentication.getName(); // from JWT token
        boolean success = userProfileUpdateService.resetPassword(email, request.getOldPassword(), request.getNewPassword());

        if (!success) {
            return ResponseEntity.badRequest().body("Old password is incorrect!");
        }
        return ResponseEntity.ok("Password updated successfully.");
    }

    // Update Profile
    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileRequest request,
                                                Authentication authentication) {
        String email = authentication.getName(); // from JWT token
        boolean success = userProfileUpdateService.updateProfile(email, request);

        if (!success) {
            return ResponseEntity.badRequest().body("Failed to update profile!");
        }
        return ResponseEntity.ok("Profile updated successfully.");
    }


}
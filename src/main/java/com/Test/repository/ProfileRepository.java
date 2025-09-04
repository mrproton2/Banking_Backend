package com.Test.repository;

import com.Test.dto.UserProfileDTO;

public interface ProfileRepository {
    UserProfileDTO getUserProfileByEmail(String email);
}

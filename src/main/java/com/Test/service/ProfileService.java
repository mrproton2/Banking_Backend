package com.Test.service;

import com.Test.dto.UserProfileDTO;

public interface ProfileService {
    UserProfileDTO getProfile(String email);

}

package com.Test.service.serviceImpl;

import com.Test.dto.UserProfileDTO;
import com.Test.repository.ProfileRepository;
import com.Test.repository.UserRepository;
import com.Test.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository,UserRepository userRepository) {

        this.profileRepository = profileRepository;
        this.userRepository=userRepository;
    }

    @Override
    public UserProfileDTO getProfile(String email) {
        return profileRepository.getUserProfileByEmail(email);
    }







}

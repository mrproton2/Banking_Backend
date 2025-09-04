package com.Test.repository;

public interface ProfileUpdateRepository {
    String getPasswordByEmail(String email);
    int updatePassword(String email, String newPassword);
    int updateProfile(String email, String name, String mobile, String address);


}

package com.Test.service;

import com.Test.dto.UserDTO;

public interface UserService {

    void registerUser(UserDTO user);
    String verifyOtp(String email, String otp,String Password);
    int crateAccountNo();
    String accountdetails(String email,int branchId,String accountType);
}

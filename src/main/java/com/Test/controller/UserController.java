package com.Test.controller;

import com.Test.dto.UserDTO;
import com.Test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String register(@RequestBody UserDTO req) {
        userService.registerUser(req);
        return "OTP sent to email!";
    }

    @PostMapping("/register/verify")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp,@RequestParam String password,@RequestParam int branchId,@RequestParam String accountType) {
        userService.verifyOtp(email, otp,password);
        return  userService.accountdetails(email,branchId,accountType);
    }










}
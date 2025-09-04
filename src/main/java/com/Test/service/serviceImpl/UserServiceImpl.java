package com.Test.service.serviceImpl;

import com.Test.dto.UserDTO;
import com.Test.repository.OtpRepository;
import com.Test.repository.UserRepository;
import com.Test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OtpRepository otpRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
        private PasswordEncoder passwordEncoder;


    private static final Random RANDOM = new Random();
    private static final int MIN = 10000000;  // 8-digit minimum
    private static final int MAX = 99999999;



    @Override
    public void registerUser(UserDTO user) {
        if (userRepo.isEmailExists(user.getEmail()).orElse(false)) {
            throw new RuntimeException("Email already exists");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RuntimeException("Password and Confirm Password do not match!");
        }

        userRepo.saveUser(user);

        // generate OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpRepo.saveOtp(user.getEmail(), otp, LocalDateTime.now().plusMinutes(5));
        sendEmail(user.getEmail(), "Banking App OTP", "Your OTP is: " + otp);


    }

    @Override
    public String verifyOtp(String email, String otp,String password) {
        Optional<String> dbOtp = otpRepo.getOtp(email);
        Optional<LocalDateTime> expiry = otpRepo.getExpiry(email);
        if (dbOtp.isEmpty() || expiry.isEmpty()) {
            return "OTP not found!";
        }

        if (expiry.get().isBefore(LocalDateTime.now())) {
            return "OTP expired!";
        }

        if (!dbOtp.get().equals(otp)) {
            return "Invalid OTP!";
        }
        String hashedPassword = passwordEncoder.encode(password);
        userRepo.markVerified(email,hashedPassword);
        sendEmail(email, "Registration Successful", "Welcome! Your account has been created verified.");
        return "User verified successfully!";

    }

    @Override
    public String accountdetails(String email, int branchId, String accountType) {
        int accountno=crateAccountNo();
        int userId = Integer.parseInt(String.valueOf(userRepo.findUserIDByEmail(email)));
       userRepo.accountDetails(accountno,branchId,userId,accountType);
        sendEmail(email, "Account Number", "Welcome! Your account has been created successfully.Your Account number is "+accountno+"Your account type is "+accountType);
       return "Account account created successfully!";
    }

    @Override
    public int crateAccountNo() {
        return nextInt(MIN, MAX + 1); // inclusive range [MIN, MAX]
    }

    private static int nextInt(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("min >= max");
        int bound = max - min;
        int r;
        do {
            r = RANDOM.nextInt(bound);
        } while (r < 0);
        return min + r;
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }

}

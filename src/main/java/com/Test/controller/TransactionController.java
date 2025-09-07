package com.Test.controller;

import com.Test.dto.BankBalanceDTO;
import com.Test.jwtutils.JwtService;
import com.Test.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JwtService jwtUtil;

    @GetMapping("/history")
    public BankBalanceDTO getTransactions(HttpServletRequest request) {
        // Extract username from JWT
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7); // remove "Bearer "
        String username = jwtUtil.extractUsername(token);
        return transactionService.getCustomerTransactions(username);
    }

    @GetMapping("/balance")
    public double getBalance(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        return transactionService.getCustomerTransactions(username).getBankBalance();
    }
}
package com.Test.repository;

import com.Test.dto.TransactionDTO;

import java.util.List;

public interface TransactionRepository {
    List<TransactionDTO> findTransactionsByUsername(String username);
    double getBalanceByUsername(String username);
    String getuserinfo(String username);
}

package com.Test.service;

import com.Test.dto.BankBalanceDTO;

public interface TransactionService {
    BankBalanceDTO getCustomerTransactions(String username);
}

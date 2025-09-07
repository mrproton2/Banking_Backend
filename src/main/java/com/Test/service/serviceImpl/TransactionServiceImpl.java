package com.Test.service.serviceImpl;

import com.Test.dto.BankBalanceDTO;
import com.Test.dto.TransactionDTO;
import com.Test.repository.TransactionRepository;
import com.Test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BankBalanceDTO getCustomerTransactions(String username) {
        List<TransactionDTO> transactions = transactionRepository.findTransactionsByUsername(username);
        double balance = transactionRepository.getBalanceByUsername(username);
//        String userinfo=transactionRepository.getuserinfo(username);
            BankBalanceDTO dto = new BankBalanceDTO();
            dto.setBankBalance(balance);
            dto.setTransactions(transactions);
            return dto;
        }
   }



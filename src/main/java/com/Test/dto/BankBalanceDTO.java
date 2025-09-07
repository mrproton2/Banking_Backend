package com.Test.dto;

import java.util.List;

public class BankBalanceDTO {

    private double bankBalance;
    private String name;
    private String branch_name;
    private List<TransactionDTO> transactions;
    private List<String> useinfo;


    public BankBalanceDTO(String name, String branch_name) {
        this.name = name;
        this.branch_name = branch_name;
    }

    public BankBalanceDTO() {

    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    @Override
    public String toString() {
        return "BankBalanceDTO{" +
                "branch_name='" + branch_name + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

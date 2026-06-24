package com.example.bank.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String accountNumber) {

        super("Account " + accountNumber + " not found");
    }
}

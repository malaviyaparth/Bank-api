package com.example.bank.exception;

public class InsufficientBalanceException extends RuntimeException{

    public
    InsufficientBalanceException(String accountNumber){
        super("Account " + accountNumber + " has insufficient Balance");
    }
}

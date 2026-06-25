package com.example.bank.service;

import com.example.bank.dto.*;
import com.example.bank.exception.AccountNotFoundException;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private AccountResponse convert(Account a) {
        return AccountResponse
                .builder()
                .accountNumber(a.getAccountNumber())
                .holderName(a.getHolderName())
                .email(a.getEmail())
                .balance(a.getBalance())
                .build();
    }

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public String createAccount(CreateAccountRequest request){

        String accountNumber = UUID.randomUUID().toString().substring(0, 10);

        accountRepository.createAccount(accountNumber, request.getHolderName(), request.getEmail());

        return accountNumber;
    }

    public AccountResponse findByAccountNumber(String accountNumber) {

        try{
            Account account = accountRepository.findByAccountNumber(accountNumber);
            return convert(account);
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public List<AccountResponse>
    getAll() {

        return accountRepository
                .findAll()
                .stream()
                .map(this::convert)
                .toList();
    }

    public void updateAccount(String accountNumber, UpdateAccountRequest request){

        int rows= accountRepository.updateAccount(accountNumber, request.getHolderName(), request.getEmail());

        if(rows==0){
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public void deleteAccount(String accountNumber){

        int rows= accountRepository.deleteAccount(accountNumber);

        if(rows==0){
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public void deposit(String accountNumber, DepositRequest request) {

        try {
            Account account = accountRepository.findByAccountNumber(accountNumber);
            BigDecimal newBalance = account.getBalance().add(request.getAmount());

            accountRepository.updateBalance(accountNumber, newBalance);
            transactionRepository.save(accountNumber, "DEPOSIT", request.getAmount(), newBalance);

        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(accountNumber);
        }

    }

    public void withdraw(String accountNumber, WithdrawRequest request) {

        try {
            Account account = accountRepository.findByAccountNumber(accountNumber);

            if (account.getBalance().compareTo(request.getAmount()) < 0) {
                throw new RuntimeException("Insufficient Balance");
            }
            BigDecimal newBalance = account.getBalance().subtract(request.getAmount());

            accountRepository.updateBalance(accountNumber, newBalance);
            transactionRepository.save(accountNumber, "WITHDRAW", request.getAmount(), newBalance);

        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(accountNumber);
        }
    }
}

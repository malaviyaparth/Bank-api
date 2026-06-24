package com.example.bank.service;

import com.example.bank.dto.AccountResponse;
import com.example.bank.dto.CreateAccountRequest;
import com.example.bank.dto.UpdateAccountRequest;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private AccountResponse convert(Account a) {
        return new AccountResponse(a.getAccountNumber(), a.getHolderName(), a.getEmail(), a.getBalance());
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String createAccount(CreateAccountRequest request){

        String accountNumber = UUID.randomUUID().toString().substring(0, 10);

        accountRepository.createAccount(accountNumber, request.getHolderName(), request.getEmail());

        return accountNumber;
    }

    public AccountResponse findByAccountNumber(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber);
        return convert(account);
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
}

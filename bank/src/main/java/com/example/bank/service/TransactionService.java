package com.example.bank.service;

import com.example.bank.dto.DepositRequest;
import com.example.bank.dto.TransactionResponse;
import com.example.bank.dto.TransferRequest;
import com.example.bank.dto.WithdrawRequest;
import com.example.bank.exception.AccountNotFoundException;
import com.example.bank.exception.InsufficientBalanceException;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    private TransactionResponse convert(Transaction transaction) {
        return TransactionResponse
                .builder()
                .accountNumber(transaction.getAccountNumber())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .balanceAfter(transaction.getBalanceAfter())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .build();
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
                throw new InsufficientBalanceException(accountNumber);
            }
            BigDecimal newBalance = account.getBalance().subtract(request.getAmount());

            accountRepository.updateBalance(accountNumber, newBalance);
            transactionRepository.save(accountNumber, "WITHDRAW", request.getAmount(), newBalance);

        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    @Transactional
    public void transfer(TransferRequest request) {

        try{
            Account from = accountRepository.findByAccountNumber(request.getFromAccount());
            Account to = accountRepository.findByAccountNumber(request.getToAccount());

            if (from.getBalance().compareTo(request.getAmount()) < 0) {
                throw new InsufficientBalanceException(request.getFromAccount());
            }

            BigDecimal fromNewBalance = from.getBalance().subtract(request.getAmount());
            BigDecimal toNewBalance = to.getBalance().add(request.getAmount());

            accountRepository.updateBalance(from.getAccountNumber(), fromNewBalance);
            accountRepository.updateBalance(to.getAccountNumber(), toNewBalance);

            transactionRepository.save(from.getAccountNumber(), "TRANSFER_DEBIT", request.getAmount(), fromNewBalance, "SUCCESS");
            transactionRepository.save(to.getAccountNumber(), "TRANSFER_CREDIT", request.getAmount(), toNewBalance, "SUCCESS");

        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Account not found");
        }
    }

    public List<TransactionResponse> history(String accountNumber, int page, int size){
        return transactionRepository
                .history(accountNumber, page, size)
                .stream()
                .map(this::convert)
                .toList();
    }
}

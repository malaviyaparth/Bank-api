package com.example.bank.controller;

import com.example.bank.dto.DepositRequest;
import com.example.bank.dto.TransferRequest;
import com.example.bank.dto.WithdrawRequest;
import com.example.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountNumber}/deposit")
    public Map<String, String> deposit(@PathVariable String accountNumber, @Valid @RequestBody DepositRequest request) {

        accountService.deposit(accountNumber, request);
        return Map.of("message", "Deposit Successful");
    }

    @PostMapping("/{accountNumber}/withdraw")
    public Map<String, String> withdraw(@PathVariable String accountNumber, @Valid @RequestBody WithdrawRequest request) {

        accountService.withdraw(accountNumber, request);
        return Map.of("message", "Withdraw Successful");
    }

    @PostMapping("/transfer")
    public Map<String, String> transfer(@Valid @RequestBody TransferRequest request) {

        accountService.transfer(request);
        return Map.of("message", "Transfer Successful");
    }
}

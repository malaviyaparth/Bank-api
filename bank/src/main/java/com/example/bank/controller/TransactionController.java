package com.example.bank.controller;

import com.example.bank.dto.DepositRequest;
import com.example.bank.dto.TransactionResponse;
import com.example.bank.dto.TransferRequest;
import com.example.bank.dto.WithdrawRequest;
import com.example.bank.model.Transaction;
import com.example.bank.service.AccountService;
import com.example.bank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit/{accountNumber}")
    public Map<String, String> deposit(@PathVariable String accountNumber, @Valid @RequestBody DepositRequest request) {

        transactionService.deposit(accountNumber, request);
        return Map.of("message", "Deposit Successful");
    }

    @PostMapping("/withdraw/{accountNumber}")
    public Map<String, String> withdraw(@PathVariable String accountNumber, @Valid @RequestBody WithdrawRequest request) {

        transactionService.withdraw(accountNumber, request);
        return Map.of("message", "Withdraw Successful");
    }

    @PostMapping("/transfer")
    public Map<String, String> transfer(@Valid @RequestBody TransferRequest request) {

        transactionService.transfer(request);
        return Map.of("message", "Transfer Successful");
    }

    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponse> history(@PathVariable String accountNumber,
                                             @RequestParam(defaultValue="0") int page,
                                             @RequestParam(defaultValue="10") int size){

        return transactionService.history(accountNumber, page, size);
    }
}

package com.example.bank.controller;

import com.example.bank.dto.AccountResponse;
import com.example.bank.dto.CreateAccountRequest;
import com.example.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Map<String, String> createAccount(@RequestBody CreateAccountRequest request){

        String accountNumber = accountService.createAccount(request);

        return Map.of("message", "Account Created", "accountNumber", accountNumber);
    }

    @GetMapping
    public List<AccountResponse> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getByAccountNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }
}

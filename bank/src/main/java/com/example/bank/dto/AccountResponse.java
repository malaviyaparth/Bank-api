package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AccountResponse{

    private String accountNumber;
    private String holderName;
    private String email;
    private BigDecimal balance;
}

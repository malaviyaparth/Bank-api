package com.example.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {

    private Integer transactionId;
    private String accountNumber;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private LocalDateTime createdAt;
}

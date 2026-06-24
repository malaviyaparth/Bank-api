package com.example.bank.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account {

    private Integer accountId;
    private String accountNumber;
    private String holderName;
    private String email;
    private BigDecimal balance;
}

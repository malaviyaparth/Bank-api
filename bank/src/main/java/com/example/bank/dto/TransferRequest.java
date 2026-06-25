package com.example.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.math.BigDecimal;

@Getter

public class TransferRequest {

    @NotNull(message = "Sender account number is required")
    private String fromAccount;
    @NotNull(message = "Receiver account number is required")
    private String toAccount;
    @NotNull(message = "Amount is reqiured")
    private BigDecimal amount;
}

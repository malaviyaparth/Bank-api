package com.example.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.math.BigDecimal;

@Getter

public class DepositRequest{

    @NotNull(message = "Amount is reqiured")
    private BigDecimal amount;
}

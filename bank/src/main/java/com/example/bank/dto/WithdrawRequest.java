package com.example.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.math.BigDecimal;

@Getter

public class WithdrawRequest{

    @NotNull(message = "Amount is reqiured")
    @DecimalMin(value="1")
    private BigDecimal amount;
}
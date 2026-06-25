package com.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class CreateAccountRequest {

    @NotBlank(message = "Account holder name is required")
    private String holderName;
    @Email(message = "Invalid email")
    private String email;
}

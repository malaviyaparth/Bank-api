package com.example.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class LoginRequest{

    @NotBlank(message = "User name is required")
    private String username;

    @Size(min = 6)
    private String password;
}

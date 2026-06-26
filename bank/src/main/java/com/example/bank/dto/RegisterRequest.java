package com.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter

public class RegisterRequest{

    @NotBlank(message = "User name is required")
    private String username;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6)
    private String password;
}

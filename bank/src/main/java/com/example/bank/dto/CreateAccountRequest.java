package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateAccountRequest {

    private String holderName;
    private String email;
}

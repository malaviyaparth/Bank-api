package com.example.bank.controller;

import com.example.bank.dto.LoginRequest;
import com.example.bank.dto.RegisterRequest;
import com.example.bank.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController{

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Map<String,String> register(@Valid @RequestBody RegisterRequest request){

        service.register(request);

        return Map.of("message", "Registered");
    }

    @PostMapping("/login")
    public Map<String,String> login(@Valid @RequestBody LoginRequest request){

        return Map.of("message", service.login(request));
    }

}

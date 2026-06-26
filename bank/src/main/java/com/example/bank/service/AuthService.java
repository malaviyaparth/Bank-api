package com.example.bank.service;

import com.example.bank.dto.LoginRequest;
import com.example.bank.dto.RegisterRequest;
import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){

        userRepository.create(request.getUsername(),
                                request.getEmail(),
                                passwordEncoder.encode(request.getPassword()),"USER");

    }

    public String login(LoginRequest request){

        try{

            User user= userRepository.findByUsername(request.getUsername());

            if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())){
                throw new RuntimeException("Invalid credentials");
            }

        } catch (EmptyResultDataAccessException exception){
            throw new RuntimeException("Invalid credentials");
        }

        return "LOGIN SUCCESS";
    }
}

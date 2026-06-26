package com.example.bank.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User{

    private Integer userId;
    private String username;
    private String email;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;

}

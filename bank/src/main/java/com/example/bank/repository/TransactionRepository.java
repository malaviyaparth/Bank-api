package com.example.bank.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(String accountNumber, String type, BigDecimal amount, BigDecimal balanceAfter) {

        String sql = "INSERT INTO transactions (account_number, type, amount, balance_after) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql, accountNumber, type, amount, balanceAfter);
    }
}

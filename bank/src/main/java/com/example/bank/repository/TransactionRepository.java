package com.example.bank.repository;

import com.example.bank.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionRowMapper transactionRowMapper;

    public TransactionRepository(JdbcTemplate jdbcTemplate, TransactionRowMapper transactionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionRowMapper = transactionRowMapper;
    }

    public int save(String accountNumber, String type, BigDecimal amount, BigDecimal balanceAfter) {

        String sql = "INSERT INTO transactions (account_number, type, amount, balance_after) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql, accountNumber, type, amount, balanceAfter);
    }

    public int save(String accountNumber, String type, BigDecimal amount, BigDecimal balanceAfter, String status) {

        String sql = "INSERT INTO transactions (account_number, type, amount, balance_after, status) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, accountNumber, type, amount, balanceAfter, status);
    }

    public List<Transaction> history(String account, int page, int size){

        String sql = "SELECT * FROM transactions WHERE account_number=? ORDER BY created_at DESC LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, transactionRowMapper, account, size, page*size);
    }
}

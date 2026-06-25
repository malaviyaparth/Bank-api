package com.example.bank.repository;

import com.example.bank.model.Transaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Transaction
                .builder()
                .transactionId(rs.getInt("transaction_id"))
                .accountNumber(rs.getString("account_number"))
                .type(rs.getString("type"))
                .amount(rs.getBigDecimal("amount"))
                .balanceAfter(rs.getBigDecimal("balance_after"))
                .status(rs.getString("status"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}

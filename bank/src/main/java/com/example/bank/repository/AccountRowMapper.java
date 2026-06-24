package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountRowMapper implements RowMapper<Account>{

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account
                .builder()
                .accountId(rs.getInt("account_id"))
                .accountNumber(rs.getString("account_number"))
                .holderName(rs.getString("holder_name"))
                .email(rs.getString("email"))
                .balance(rs.getBigDecimal("balance"))
                .build();
    }
}

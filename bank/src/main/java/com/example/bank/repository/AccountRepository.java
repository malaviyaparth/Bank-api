package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AccountRowMapper accountRowMapper;

    public AccountRepository(JdbcTemplate jdbcTemplate, AccountRowMapper accountRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountRowMapper = accountRowMapper;
    }

    public int createAccount(String accountNumber, String holderName, String email){

        String sql = "INSERT INTO accounts(account_number, holder_name, email, balance ) VALUES (?, ?, ?, 0)";

        return jdbcTemplate.update(sql, accountNumber, holderName, email);

    }

    public Account findByAccountNumber(String accountNumber) {

        String sql = "SELECT * FROM accounts WHERE account_number = ? ";

        try{
            return jdbcTemplate.queryForObject(sql, accountRowMapper, accountNumber);
        }
        catch (EmptyResultDataAccessException exception){
            return null;
        }
    }

    public List<Account> findAll() {

        String sql = "SELECT * FROM accounts ORDER BY account_id";

        return jdbcTemplate.query(sql, accountRowMapper);
    }

    public int updateAccount(String accountNumber, String holder, String email){

        String sql= "UPDATE accounts SET holder_name=?, email=? WHERE account_number=?";

        return jdbcTemplate.update(sql, holder, email, accountNumber);
    }

    public int deleteAccount(String accountNumber){

        String sql= "DELETE FROM accounts WHERE account_number=?";

        return jdbcTemplate.update(sql, accountNumber);
    }
}

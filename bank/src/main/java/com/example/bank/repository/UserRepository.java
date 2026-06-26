package com.example.bank.repository;

import com.example.bank.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public int create(String username, String email, String password, String role){

        String sql= "INSERT INTO users (username, email, password_hash, role) VALUES ( ?, ?, ?, ? )";

        return jdbcTemplate.update(sql, username, email, password, role);
    }

    public User findByUsername(String username) throws EmptyResultDataAccessException {

        String sql= "SELECT * FROM users WHERE username=?";

        return jdbcTemplate.queryForObject(sql, userRowMapper, username);
    }
}

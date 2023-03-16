package com.example.microgram.dao;

import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        String sql = "select * from customers ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findUserByName(String name) {
        String sql = "select * from customers where name =" + "'" + name + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findUserByEmail(String email) {
        String sql = "select * from customers where email ="  + "'" + email + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
//
    public List<User> findUserByAccount(String account) {
        String sql = "select * from customers where account ="  + "'" + account + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}

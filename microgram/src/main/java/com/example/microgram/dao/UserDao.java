package com.example.microgram.dao;

import com.example.microgram.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component

public class UserDao extends BaseDao {

    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists usr\n" +
                "(\n" +
                "    id          bigserial primary key,\n" +
                "    name       text,\n" +
                "    account        text,\n" +
                "    email        text,\n" +
                "    password        text,\n" +
                "    counter_publication int,\n" +
                "    counter_follower int,\n" +
                "    counter_following int\n" +
                ");");
    }

    public void saveAll(List<User> users) {
        String sql = "insert into usr(email, name) " +
                "values(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getEmail());
                ps.setString(2, users.get(i).getName());
            }

            public int getBatchSize() {
                return users.size();
            }
        });
    }

    public void save(User user) {
        String sql = "insert into usr(name, account, email, password, counter_publication, " +
                "counter_follower, counter_following) " +
                "values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAccount());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getCounterPublication());
            ps.setInt(6, user.getCounterFollower());
            ps.setInt(7, user.getCounterFollowing());
            return ps;
        });
    }

    public void deleteAll() {
        String sql = "delete from usr";
        jdbcTemplate.update(sql);
    }

    public Optional<User> findUserByName(String name) {
        String sql = "select * " +
                "from usr " +
                "where name = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)
        ));
    }

    public Optional<User> findUserByEmail(String email) {
        String sql = "select * " +
                "from usr " +
                "where email = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
        ));
    }


    public Optional<User> findUserByAccount(String account) {
        String sql = "select * " +
                "from usr " +
                "where account = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), account)
        ));
    }

    public List<User> getUsers() {
        String sql = "select * from usr";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findUserById(Long id) {
        String sql = "select * " +
                "from usr " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
        ));

    }

//    public List<User> findUserByName(String name) {
//        String sql = "select * from customers where name =" + "'" + name + "'";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//    }
//
//    public List<User> findUserByEmail(String email) {
//        String sql = "select * from customers where email ="  + "'" + email + "'";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//    }
////
//    public List<User> findUserByAccount(String account) {
//        String sql = "select * from customers where account ="  + "'" + account + "'";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//    }
}

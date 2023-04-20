package com.example.microgram.dao;

import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class FollowDao extends BaseDao {
    public FollowDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists follows\n" +
                "(\n" +
                "    id             bigserial primary key,\n" +
                "    follower      bigint,\n" +
                "    following     bigint,\n" +
                "    date_time timestamp default current_timestamp,\n" +
                "    foreign key (follower) references usr (id),\n" +
                "    foreign key (following) references usr (id)\n" +
                ");");
    }

    public void deleteAll() {
        String sql = "delete from follows";
        jdbcTemplate.update(sql);
    }

    public void save(Follow follow) {
        String sql = "insert into follows(follower, following,  date_time) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, follow.getFollower());
            ps.setLong(2, follow.getFollowing());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }


    public Optional<Follow> findById(Long id) {
        String sql = "select * " +
                "from follows " +
                "where following = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Follow.class), id)));
    }
}

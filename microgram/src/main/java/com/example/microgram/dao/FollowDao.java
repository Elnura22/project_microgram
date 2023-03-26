package com.example.microgram.dao;

import com.example.microgram.entity.Follow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
            ps.setInt(1, follow.getFollower());
            ps.setInt(2, follow.getFollowing());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }
}

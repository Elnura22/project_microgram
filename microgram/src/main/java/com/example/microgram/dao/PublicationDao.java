package com.example.microgram.dao;

import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Publication> getPublication(int id) {
        String sql = "select * from publications as p " +
                "inner join users as u on p.id = u.counterPublication" +
                "where counterPublication = " + id ;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> getPublicationsByFollowings(Follow follow){
        String sql = "select * from publications as p " +
                "inner join user as u on p.id = u.counterPublication" +
                "where u = " + follow.getFollowing();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }
}

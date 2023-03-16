package com.example.microgram.dao;

import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikedDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Liked> getLikes(Publication publication) {
        String sql = "select * from liked " +
                "where publication = " + publication ;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Liked.class));
    }
}

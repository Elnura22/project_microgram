package com.example.microgram.dao;

import com.example.microgram.entity.Liked;
import com.example.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class LikedDao extends BaseDao {

    public LikedDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists liked\n" +
                "(\n" +
                "    id                bigserial primary key,\n" +
                "    who_liked         bigint,\n" +
                "    which_publication bigint,\n" +
                "    date_time         timestamp default current_timestamp,\n" +
                "    foreign key (who_liked) references usr (id),\n" +
                "    foreign key (which_publication) references publications (id)\n" +
                ");");

    }

    public List<Liked> getLikes(Publication publication) {
        String sql = "select * from liked " +
                "where which_publication = " + publication ;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Liked.class));
    }

    public void deleteAll() {
        String sql = "delete from liked";
        jdbcTemplate.update(sql);
    }

    public void save(Liked like) {
        String sql = "insert into liked(who_liked, which_publication, date_time) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, like.getWhoLiked());
            ps.setInt(2,like.getWhichPublication());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }
}

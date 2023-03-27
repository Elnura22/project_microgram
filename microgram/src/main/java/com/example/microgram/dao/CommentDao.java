package com.example.microgram.dao;

import com.example.microgram.entity.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class CommentDao extends BaseDao {
    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists comments\n" +
                "(\n" +
                "    id             bigserial primary key,\n" +
                "    text           text,\n" +
                "    date_time      timestamp default current_timestamp,\n" +
                "    publication_id bigint,\n" +
                "    user_id        bigint,\n" +
                "    foreign key (publication_id) references publications (id),\n" +
                "    foreign key (user_id) references usr (id)\n" +
                ");");
    }


    public void deleteAll() {
        String sql = "delete from comments";
        jdbcTemplate.update(sql);
    }

    public void save(Comment comment) {
        String sql = "insert into comments(text,publication_id,user_id, date_time) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comment.getText());
            ps.setInt(2,comment.getPublicationId());
            ps.setInt(3,comment.getUserId());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

    public void deleteById(Long commentId) {
        String sql = "delete from comments where id = ?";
        jdbcTemplate.update(sql, commentId);
    }
}

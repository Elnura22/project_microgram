package com.example.microgram.dao;

import com.example.microgram.entity.Comment;
import com.example.microgram.entity.Publication;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CommentDao extends BaseDao {
    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
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
            ps.setLong(2,comment.getPublicationId());
            ps.setLong(3,comment.getUserId());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

    public void deleteById(Long commentId) {
        String sql = "delete from comments where id = ?";
        jdbcTemplate.update(sql, commentId);
    }

    public Optional<Comment> findById(Long id) {
        String sql = "select * " +
                "from comments " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class), id)));
    }

    public void deleteCommentByPublicationId(Long id) {
        String sql = "delete from comments where publication_id=?";
        jdbcTemplate.update(sql, id);
    }

    public List<Comment> getAllComments() {
        String sql = "select * from comments";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }
}

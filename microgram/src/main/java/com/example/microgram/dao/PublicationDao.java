package com.example.microgram.dao;

import com.example.microgram.entity.Follow;
import com.example.microgram.entity.Publication;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PublicationDao extends BaseDao {
    public PublicationDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists publications\n" +
                "(\n" +
                "    id    bigserial primary key,\n" +
                "    image varchar not null,\n" +
                "    description varchar not null,\n" +
                "    user_id bigint,\n" +
                "    date_time timestamp DEFAULT CURRENT_TIMESTAMP,\n" +
                "    foreign key (user_id) references usr(id)\n" +
                ");");
    }

    public void save(Publication publication) {
        String sql = "insert into publications(image, description, user_id, date_time) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, publication.getImage());
            ps.setString(2, publication.getDescription());
            ps.setLong(3, publication.getUserId());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

//    public List<Publication> getListOfPublications() {
//        String sql = "select * from publications";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
//    }
    public void deleteById(Long publicationId) {
        String sql = "delete from publications p where p.id = ?";
        jdbcTemplate.update(sql, publicationId);
    }

    public void deleteAll() {
        String sql = "delete from publications";
        jdbcTemplate.update(sql);
    }
    public void saveAll(List<Publication> publications) {
        String sql = "insert into publications(email, name) " +
                "values(?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, publications.get(i).getDescription());
                ps.setString(2, publications.get(i).getDescription());
            }

            public int getBatchSize() {
                return publications.size();
            }
        });
    }

    public List<Publication> getPublication(int id) {
        String sql = "select * from publications as p " +
                "inner join users as u on p.id = u.counterPublication" +
                "where counterPublication = " + id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> getOtherUsersPublication(int id) {
        String sql = "select * from publications\n" +
                "where user_id = " + id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }


    public List<Publication> getPublicationsByFollowings(Follow follow) {
        String sql = "select * from publications as p " +
                "inner join user as u on p.id = u.counterPublication" +
                "where u = " + follow.getFollowing();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public Optional<Publication> findById(Long id) {
        String sql = "select * " +
                "from publications " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class), id)));
    }

    public List<Publication> getAllPublications() {
        String sql = "select * from publications";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }
}

//        this.jdbcTemplate.update("update table set name = ? where id = ?", name, id);
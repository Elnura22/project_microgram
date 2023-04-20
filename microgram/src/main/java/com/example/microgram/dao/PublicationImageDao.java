//package com.example.microgram.dao;
//
//import com.example.microgram.entity.PublicationImage;
//import org.springframework.dao.support.DataAccessUtils;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Component;
//
//import java.sql.PreparedStatement;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Optional;
//
//@Component
//public class PublicationImageDao extends BaseDao{
//    public PublicationImageDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        super(jdbcTemplate, namedParameterJdbcTemplate);
//    }
//
//    @Override
//    public void createTable() {
//        jdbcTemplate.execute("create table if not exists publication_image\n" +
//                "(\n" +
//                "    id          bigserial primary key,\n" +
//                "    name        varchar,\n" +
//                "    poster_data bytea\n" +
//                ");");
//
//    }
//
//    public Optional<PublicationImage> findById(Long id) {
//        String sql = "select * " +
//                "from publication_image " +
//                "where id = ?";
//        return Optional.ofNullable(DataAccessUtils.singleResult(
//                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PublicationImage.class), id)
//        ));
//    }
//
//    public Long save(PublicationImage image) {
//        String sql = "insert into publication_image(name, poster_data) " +
//                "values(?,?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
//            ps.setString(1, image.getName());
//            ps.setBytes(2, image.getPosterData());
//            return ps;
//        }, keyHolder);
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();
//    }
//}

package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.PublishingHouseRep;
import com.example.bookstore_course_work.models.PublishingHouse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublishingHouseRepository implements PublishingHouseRep {
    private final JdbcTemplate jdbcTemplate;

    public PublishingHouseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<PublishingHouse> publishingHouseRowMapper = (rs, rowNum) -> {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setId(rs.getInt("id"));
        publishingHouse.setNameOfPublishingHouse(rs.getString("nameofpublishinghouse"));
        return publishingHouse;
    };
    @Override
    public Optional<PublishingHouse> findById(int id) {
        String sql = "select * from publishinghouse where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, publishingHouseRowMapper, id));
    }

    @Override
    public List<PublishingHouse> findAll() {
        String sql = "select * from publishinghouse";
        return jdbcTemplate.query(sql, publishingHouseRowMapper);
    }

    @Override
    public void save(PublishingHouse publishingHouse) {
        String sql = "insert into publishinghouse (nameofpublishinghouse) values (?)";
        jdbcTemplate.update(sql,publishingHouse.getNameOfPublishingHouse());
    }

    @Override
    public void update(PublishingHouse publishingHouse, int id) {
        String sql = "update publishinghouse set nameofpublishinghouse = ? where id = ?";
        jdbcTemplate.update(sql,publishingHouse.getNameOfPublishingHouse(),id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from publishinghouse where id = ?";
        jdbcTemplate.update(sql, id);
    }
}

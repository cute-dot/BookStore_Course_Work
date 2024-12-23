package com.example.bookstore_course_work.repository;

import com.example.bookstore_course_work.interfaces.StreetRep;
import com.example.bookstore_course_work.models.Street;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StreetRepository implements StreetRep {

    private final JdbcTemplate jdbcTemplate;

    public StreetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Street> streetRowMapper = (rs, rowNum) -> {
        Street street = new Street();
        street.setId(rs.getInt("id"));
        street.setStreetName(rs.getString("streetname"));
        return street;
    };
    @Override
    public Optional<Street> findById(int id) {
        String sql = "select * from street where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, streetRowMapper, id));
    }

    @Override
    public List<Street> findAll() {
        String sql = "select * from street";
        return jdbcTemplate.query(sql, streetRowMapper);
    }

    @Override
    public void save(Street street) {
        String sql = "insert into street ( streetname) values (?);";
        jdbcTemplate.update(sql,street.getStreetName());
    }

    @Override
    public void update(Street street, int id) {
        String sql = "update street set streetname = ? where id = ?";
        jdbcTemplate.update(sql, street.getStreetName(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from street where id = ?";
        jdbcTemplate.update(sql, id);
    }
}

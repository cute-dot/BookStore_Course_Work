package com.example.bookstore_course_work.repository;


import com.example.bookstore_course_work.interfaces.CityRep;
import com.example.bookstore_course_work.models.City;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository implements CityRep {

    private final JdbcTemplate jdbcTemplate;

    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<City> cityRowMapper = (rs, rowNum) -> {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setCityName(rs.getString("cityname"));
        return city;
    };

    @Override
    public Optional<City> findById(int id) {
        String sql = "select * from city where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, cityRowMapper, id));
    }

    @Override
    public List<City> findAll() {
        String sql = "select * from city";
        return jdbcTemplate.query(sql, cityRowMapper);
    }

    @Override
    public void save(City city) {
        String sql = "insert into city (cityname) values (?)";
        jdbcTemplate.update(sql, city.getCityName());
    }

    @Override
    public void update(City city, int id) {
        String sql = "update city set cityname = ? where id = ?";
        jdbcTemplate.update(sql, city.getCityName(), id );
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from city where id = ?";
        jdbcTemplate.update(sql, id);
    }
}

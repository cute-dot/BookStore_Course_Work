package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.City;

import java.util.List;
import java.util.Optional;

public interface CityRep {
    Optional<City> findById(int id);
    List<City> findAll();
    void save(City city);
    void update(City city, int id);
    void deleteById(int id);
}

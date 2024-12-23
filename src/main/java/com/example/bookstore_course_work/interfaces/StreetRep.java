package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Street;

import java.util.List;
import java.util.Optional;

public interface StreetRep {
    Optional<Street> findById(int id);
    List<Street> findAll();
    void save(Street street);
    void update(Street street, int id);
    void deleteById(int id);
}

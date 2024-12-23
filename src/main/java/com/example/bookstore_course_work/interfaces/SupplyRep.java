package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyRep {
    Optional<Supply> findById(int id);
    List<Supply> findAll();
    void save(Supply supply);
    void update(Supply supply, int id);
    void deleteById(int id);
}

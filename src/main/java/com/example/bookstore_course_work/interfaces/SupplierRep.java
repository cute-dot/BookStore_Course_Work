package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRep {
    Optional<Supplier> findById(int id);
    List<Supplier> findAll();
    void save(Supplier supplier);
    void update(Supplier supplier, int id);
    void deleteById(int id);
}

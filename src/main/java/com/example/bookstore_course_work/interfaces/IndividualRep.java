package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Individual;

import java.util.List;
import java.util.Optional;

public interface IndividualRep {
    Optional<Individual> findById(int id);
    List<Individual> findAll();
    void save(Individual individual);
    void update(Individual individual, int id);
    void deleteById(int id);
}

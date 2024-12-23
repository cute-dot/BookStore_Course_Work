package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRep {
    Optional<Bank> findById(int id);
    List<Bank> findAll();
    void save(Bank bank);
    void update(Bank bank, int id);
    void deleteById(int id);
}

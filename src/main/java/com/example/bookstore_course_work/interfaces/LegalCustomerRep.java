package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.LegalCustomer;

import java.util.List;
import java.util.Optional;

public interface LegalCustomerRep{
    Optional<LegalCustomer> findById(int id);
    List<LegalCustomer> findAll();
    void save(LegalCustomer legalCustomer);
    void update(LegalCustomer legalCustomer, int id);
    void deleteById(int id);
}

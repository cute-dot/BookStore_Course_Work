package com.example.bookstore_course_work.service;

import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.LegalCustomer;
import com.example.bookstore_course_work.repository.LegalCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCustomerService {
    private final LegalCustomerRepository legalCustomerRepository;

    @Autowired
    public LegalCustomerService(LegalCustomerRepository legalCustomerRepository) {
        this.legalCustomerRepository = legalCustomerRepository;
    }
    public Optional<LegalCustomer> getLegalCustomerById(int id) {
        return legalCustomerRepository.findById(id);
    }

    public List<LegalCustomer> getAllLegalCustomers() {
        return legalCustomerRepository.findAll();
    }

    public void addLegalCustomer(LegalCustomer legalCustomer) {
        legalCustomerRepository.save(legalCustomer);
    }

    public void updateLegalCustomer(LegalCustomer legalCustomer, int id) {
        legalCustomerRepository.update(legalCustomer, id);
    }

    public void deleteLegalCustomer(int id) {
        legalCustomerRepository.deleteById(id);
    }
}

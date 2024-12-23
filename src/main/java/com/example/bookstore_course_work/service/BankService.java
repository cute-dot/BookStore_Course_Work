package com.example.bookstore_course_work.service;

import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Bank;
import com.example.bookstore_course_work.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Optional<Bank> getBankById(int id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public void addAuthor(Bank bank) {
        bankRepository.save(bank);
    }

    public void updateBank(Bank bank, int id) {
        bankRepository.update(bank, id);
    }

    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}

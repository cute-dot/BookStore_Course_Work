package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Supplier;
import com.example.bookstore_course_work.models.Supply;
import com.example.bookstore_course_work.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyService {
    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public Optional<Supply> getSupplyById(int id) {
        return supplyRepository.findById(id);
    }

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    public void addSupply(Supply supply) {
        supplyRepository.save(supply);
    }

    public void updateSupply(Supply supply, int id) {
        supplyRepository.update(supply, id);
    }

    public void deleteSupply(int id) {
        supplyRepository.deleteById(id);
    }
}

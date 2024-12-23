package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Supplier;
import com.example.bookstore_course_work.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void updateSupplier(Supplier supplier, int id) {
        supplierRepository.update(supplier, id);
    }

    public void deleteSupplier(int id) {
        supplierRepository.deleteById(id);
    }

}

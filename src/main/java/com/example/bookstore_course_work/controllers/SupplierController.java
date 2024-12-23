package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Book;
import com.example.bookstore_course_work.models.Street;
import com.example.bookstore_course_work.models.Supplier;
import com.example.bookstore_course_work.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable int id){
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if(supplier.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/addSupplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        supplierService.addSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
        supplierService.updateSupplier(supplier, id);
        return ResponseEntity.ok(supplier);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuppler(@PathVariable int id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

}

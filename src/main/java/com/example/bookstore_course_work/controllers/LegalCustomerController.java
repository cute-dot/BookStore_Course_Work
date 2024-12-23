package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.models.LegalCustomer;
import com.example.bookstore_course_work.service.LegalCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/legalCustomers")
public class LegalCustomerController {

    private final LegalCustomerService legalCustomerService;

    public LegalCustomerController(LegalCustomerService legalCustomerService) {
        this.legalCustomerService = legalCustomerService;
    }

    @GetMapping
    public List<LegalCustomer> getAllLegalCustomers() {
        return legalCustomerService.getAllLegalCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LegalCustomer>> getLegalCustomerById(@PathVariable int id){
        Optional<LegalCustomer> legalCustomer = legalCustomerService.getLegalCustomerById(id);
        if(legalCustomer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(legalCustomer);
    }


    @PostMapping("/addLegal")
    public ResponseEntity<LegalCustomer> addLegal(@RequestBody LegalCustomer legalCustomer) {
        legalCustomerService.addLegalCustomer(legalCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(legalCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegalCustomer> updateLegal(@PathVariable int id, @RequestBody LegalCustomer legalCustomer) {
        legalCustomerService.updateLegalCustomer(legalCustomer, id);
        return ResponseEntity.ok(legalCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        legalCustomerService.deleteLegalCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

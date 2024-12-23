package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Bank;
import com.example.bookstore_course_work.models.Book;
import com.example.bookstore_course_work.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bank>> getBankById(@PathVariable int id){
        Optional<Bank> bank = bankService.getBankById(id);
        if(bank.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(bank);
    }
    @PostMapping("/addBank")
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        bankService.addAuthor(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(bank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable int id, @RequestBody Bank bank) {
        bankService.updateBank(bank, id);
        return ResponseEntity.ok(bank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

}

package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.models.Street;
import com.example.bookstore_course_work.models.Supply;
import com.example.bookstore_course_work.service.SupplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {
    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping
    public List<Supply> getAllSupplies() {
        return supplyService.getAllSupplies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Supply>> getStreetById(@PathVariable int id){
        Optional<Supply> supply = supplyService.getSupplyById(id);
        if(supply.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(supply);
    }

    @PostMapping("/addSupply")
    public ResponseEntity<Supply> addSupply(@RequestBody Supply supply) {
        supplyService.addSupply(supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(supply);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable int id, @RequestBody Supply supply) {
        supplyService.updateSupply(supply, id);
        return ResponseEntity.ok(supply);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable int id) {
        supplyService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }
}

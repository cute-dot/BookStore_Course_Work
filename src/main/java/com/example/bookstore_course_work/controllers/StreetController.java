package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Order;
import com.example.bookstore_course_work.models.Street;
import com.example.bookstore_course_work.models.Supplier;
import com.example.bookstore_course_work.service.StreetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/streets")
public class StreetController {

    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public List<Street> getAllStreets() {
        return streetService.getAllStreets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Street>> getStreetById(@PathVariable int id){
        Optional<Street> street = streetService.getStreetById(id);
        if(street.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(street);
    }


    @PostMapping("/addStreet")
    public ResponseEntity<Street> addStreet(@RequestBody Street street) {
        streetService.addStreet(street);
        return ResponseEntity.status(HttpStatus.CREATED).body(street);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Street> updateStreet(@PathVariable int id, @RequestBody Street street) {
        streetService.updateStreet(street, id);
        return ResponseEntity.ok(street);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreet(@PathVariable int id) {
        streetService.deleteStreet(id);
        return ResponseEntity.noContent().build();
    }
}

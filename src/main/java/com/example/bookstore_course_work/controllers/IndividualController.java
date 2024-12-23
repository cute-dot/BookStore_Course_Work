package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Book;
import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.service.IndividualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/individuals")
public class IndividualController {
    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @GetMapping
    public List<Individual> getAllIndividuals() {
        return individualService.getAllIndividuals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Individual>> getIndividualById(@PathVariable int id){
        Optional<Individual> individual = individualService.getIndividualById(id);
        if(individual.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(individual);
    }

    @PostMapping("/addIndividual")
    public ResponseEntity<Individual> addBook(@RequestBody Individual individual) {
        individualService.addIndividual(individual);
        return ResponseEntity.status(HttpStatus.CREATED).body(individual);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Individual> updateBook(@PathVariable int id, @RequestBody Individual individual) {
        individualService.updateIndividual(individual, id);
        return ResponseEntity.ok(individual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        individualService.deleteIndividual(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllCities() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable int id){
        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(author);
    }
    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        authorService.updateAuthor(author, id);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

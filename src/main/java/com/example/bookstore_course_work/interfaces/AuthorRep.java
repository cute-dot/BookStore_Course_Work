package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRep {
    Optional<Author> findById(int id);
    List<Author> findAll();
    void save(Author author);
    void update(Author author, int id);
    void deleteById(int id);
}

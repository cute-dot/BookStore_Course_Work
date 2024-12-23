package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRep {
    Optional<Book> findById(int id);
    List<Book> findAll();
    void save(Book book);
    void update(Book book, int id);
    void deleteById(int id);
}

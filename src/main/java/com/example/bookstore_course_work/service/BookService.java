package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Book;
import com.example.bookstore_course_work.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book, int id) {
        bookRepository.update(book, id);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}

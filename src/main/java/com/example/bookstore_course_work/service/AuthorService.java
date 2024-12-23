package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(Author author, int id) {
        authorRepository.update(author, id);
    }

    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}

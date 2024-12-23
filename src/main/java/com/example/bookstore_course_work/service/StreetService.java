package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Street;
import com.example.bookstore_course_work.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {

    private final StreetRepository streetRepository;

    @Autowired
    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public Optional<Street> getStreetById(int id) {
        return streetRepository.findById(id);
    }

    public List<Street> getAllStreets() {
        return streetRepository.findAll();
    }

    public void addStreet(Street street) {
        streetRepository.save(street);
    }

    public void updateStreet(Street street, int id) {
        streetRepository.update(street, id);
    }

    public void deleteStreet(int id) {
        streetRepository.deleteById(id);
    }
}

package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndividualService {
    private final IndividualRepository individualRepository;

    @Autowired
    public IndividualService(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    public Optional<Individual> getIndividualById(int id) {
        return individualRepository.findById(id);
    }

    public List<Individual> getAllIndividuals() {
        return individualRepository.findAll();
    }

    public void addIndividual(Individual individual) {
        individualRepository.save(individual);
    }

    public void updateIndividual(Individual individual, int id) {
        individualRepository.update(individual, id);
    }

    public void deleteIndividual(int id) {
        individualRepository.deleteById(id);
    }
}

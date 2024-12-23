package com.example.bookstore_course_work.service;

import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.PublishingHouse;
import com.example.bookstore_course_work.repository.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public PublisingHouseService(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }
    public Optional<PublishingHouse> getPHById(int id) {
        return publishingHouseRepository.findById(id);
    }

    public List<PublishingHouse> getAllPH() {
        return publishingHouseRepository.findAll();
    }

    public void addPH(PublishingHouse publishingHouse) {
        publishingHouseRepository.save(publishingHouse);
    }

    public void updatePH(PublishingHouse publishingHouse, int id) {
        publishingHouseRepository.update(publishingHouse, id);
    }

    public void deletePH(int id) {
        publishingHouseRepository.deleteById(id);
    }
}

package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.PublishingHouse;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseRep {
    Optional<PublishingHouse> findById(int id);
    List<PublishingHouse> findAll();
    void save(PublishingHouse publishingHouse);
    void update(PublishingHouse publishingHouse, int id);
    void deleteById(int id);
}

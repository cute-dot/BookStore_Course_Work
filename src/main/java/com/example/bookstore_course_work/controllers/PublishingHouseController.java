package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Order;
import com.example.bookstore_course_work.models.PublishingHouse;
import com.example.bookstore_course_work.service.PublisingHouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishingHouses")
public class PublishingHouseController {
    private final PublisingHouseService publisingHouseService;

    public PublishingHouseController(PublisingHouseService publisingHouseService) {
        this.publisingHouseService = publisingHouseService;
    }

    @GetMapping
    public List<PublishingHouse> getAllPH() {
        return publisingHouseService.getAllPH();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PublishingHouse>> getPHById(@PathVariable int id){
        Optional<PublishingHouse> publishingHouse = publisingHouseService.getPHById(id);
        if(publishingHouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(publishingHouse);
    }

    @PostMapping("/addPublishingHouse")
    public ResponseEntity<PublishingHouse> addPB(@RequestBody PublishingHouse publishingHouse) {
        publisingHouseService.addPH(publishingHouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(publishingHouse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouse> updatePB(@PathVariable int id, @RequestBody PublishingHouse publishingHouse) {
        publisingHouseService.updatePH(publishingHouse, id);
        return ResponseEntity.ok(publishingHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        publisingHouseService.deletePH(id);
        return ResponseEntity.noContent().build();
    }
}

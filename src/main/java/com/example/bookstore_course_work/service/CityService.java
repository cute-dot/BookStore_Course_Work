package com.example.bookstore_course_work.service;

import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.City;
import com.example.bookstore_course_work.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCityById(int id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void addCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(City city, int id) {
        cityRepository.update(city, id);
    }

    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }
}

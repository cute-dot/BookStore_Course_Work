package com.example.bookstore_course_work.interfaces;

import com.example.bookstore_course_work.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRep {

    Optional<Order> findById(int id);
    List<Order> findAll();
    void save(Order order);
    void update(Order order, int id);
    void deleteById(int id);
}

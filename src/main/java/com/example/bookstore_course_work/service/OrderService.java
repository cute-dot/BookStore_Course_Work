package com.example.bookstore_course_work.service;


import com.example.bookstore_course_work.models.Author;
import com.example.bookstore_course_work.models.Order;
import com.example.bookstore_course_work.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order, int id) {
        orderRepository.update(order, id);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}

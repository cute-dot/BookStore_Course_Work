package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.Book;
import com.example.bookstore_course_work.models.Individual;
import com.example.bookstore_course_work.models.Order;
import com.example.bookstore_course_work.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable int id){
        Optional<Order> order = orderService.getOrderById(id);
        if(order.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
//@PostMapping("/addOrder")
//public ResponseEntity<String> addOrder(@RequestBody Order order) {
//    // Проверяем, что хотя бы одно из полей указано
////    if ((order.getIndividualId() == null && order.getLegalCustomerId() == null) ||
////            (order.getIndividualId() != null && order.getLegalCustomerId() != null)) {
////        return ResponseEntity.badRequest().body("Укажите либо ID физического лица, либо ID юридического лица, но не оба.");
////    }
//    try {
//        // Если ID пусто или равно 0, выставляем его в null
//        if (order.getIndividualId() == 0) {
//            order.setIndividualId(null);
//        }
//        if (order.getLegalCustomerId() == 0) {
//            order.setLegalCustomerId(null);
//        }
//
//        // Сохранение заказа
//        orderService.addOrder(order);
//
//        return ResponseEntity.ok("Заказ успешно добавлен");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при добавлении заказа: " + e.getMessage());
//    }
//}

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateBook(@PathVariable int id, @RequestBody Order order) {
        orderService.updateOrder(order, id);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

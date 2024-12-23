//package com.example.bookstore_course_work.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.security.core.Authentication;
//
//@Controller
//public class HomeController {
//
//    // Главная страница после авторизации
////    @GetMapping("/home")
////    public String homePage(Authentication authentication, Model model) {
////        // Добавление информации о текущем пользователе в модель
////        String username = authentication.getName(); // Имя текущего пользователя
////        model.addAttribute("username", username);
////        return "home";
////    }
//    @GetMapping("/home")
//    public String homepage(){
//        return "home";
//    }
//
//
//    // Если нужно, можно добавить дополнительные страницы
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin";
//    }
//
//    @GetMapping("/user")
//    public String userPage() {
//        return "user";
//    }
//}

package com.example.bookstore_course_work.authorization.controllers;

import com.example.bookstore_course_work.authorization.models.User;
import com.example.bookstore_course_work.authorization.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/")
public class AuthController {
    private final AuthorityService authService;

    @Autowired
    public AuthController(AuthorityService authorityService) {
        this.authService = authorityService;
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "/login";
    }
    @PostMapping("/login")
    public String redirectToPages(@RequestParam Map<String,String> input) {
        User user = authService.authenticate(input.get("username"), input.get("password"));
        if (user != null) {
            String role = user.getRole();
            switch (role) {
                case "manager_work_with_supply" -> {
                    return "/managerwws";
                }
                case "admin" -> {
                    return "/admin";
                }
                case "manager_work_with_clients" -> {
                    return "/managerC";
                }
            }
        }
        return "/login"; // неверные данные
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/"; // Перенаправляем на страницу авторизации
    }
}


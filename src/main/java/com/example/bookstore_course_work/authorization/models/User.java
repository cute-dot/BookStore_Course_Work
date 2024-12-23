package com.example.bookstore_course_work.authorization.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password) {
    }

    public User(String admin, String password, String roleAdmin) {
    }
}

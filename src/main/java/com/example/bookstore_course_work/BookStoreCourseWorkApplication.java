package com.example.bookstore_course_work;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.SQLWarningException;

@SpringBootApplication
public class BookStoreCourseWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreCourseWorkApplication.class, args);

        String password = "123";

        // Генерация хэша для пароля
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // Вывод захэшированного пароля
        System.out.println("Хэш пароля: " + hashedPassword);
    }




}

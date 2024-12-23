package com.example.bookstore_course_work.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/execute-sql")
@CrossOrigin
public class ChangePasswordController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public List<Map<String, Object>> executeSQL(@RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        try {
            return jdbcTemplate.queryForList(sql);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения SQL-запроса: " + e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        String checkPasswordSql = "SELECT password FROM users WHERE username = ?";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(checkPasswordSql, username);
        Map<String, String> response = new HashMap<>();

        if (result.isEmpty() || !BCrypt.checkpw(oldPassword, (String) result.get(0).get("password"))) {
            response.put("message", "Неверный старый пароль");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        String updatePasswordSql = "UPDATE users SET password = ? WHERE username = ?";
        int rowsAffected = jdbcTemplate.update(updatePasswordSql, hashedPassword, username);

        if (rowsAffected > 0) {
            response.put("message", "Пароль успешно изменён");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Ошибка при обновлении пароля");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

package com.example.bookstore_course_work.controllers;


import com.example.bookstore_course_work.models.TableDataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/documentSQL")
public class DocumentSQLController {

    private final JdbcTemplate jdbcTemplate;

    public DocumentSQLController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/download")
    public ResponseEntity<TableDataResponse> downloadDocument(@RequestBody Map<String, String> requestBody) {
        String sqlQuery = requestBody.get("sql");

        String tableName = extractTableNameFromSQL(sqlQuery);

        List<Map<String, Object>> data = getDataFromDatabase(sqlQuery);

        TableDataResponse response = new TableDataResponse(tableName, data);

        return ResponseEntity.ok(response);
    }

    private List<Map<String, Object>> getDataFromDatabase(String sqlQuery) {
        // Выполняем SQL запрос и получаем результат
        // Используем JdbcTemplate для выполнения запроса
        return jdbcTemplate.queryForList(sqlQuery);
    }

    private String extractTableNameFromSQL(String sql) {
        // Здесь можно извлечь имя таблицы из SQL-запроса
        // Пример очень простого извлечения имени таблицы:
        String[] parts = sql.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if ("from".equalsIgnoreCase(parts[i])) {
                return parts[i + 1];
            }
        }
        return "unknown";  // Если не удалось извлечь, возвращаем "unknown"
    }

    @PostMapping("/execute")
    public ResponseEntity<Map<String, Object>> executeSql(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        Map<String, Object> response = new HashMap<>();

        try {
            // Выполняем SQL-запрос и получаем результат
            List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

            // Формируем список колонок из первой строки результата
            List<String> columns = new ArrayList<>();
            if (!results.isEmpty()) {
                columns.addAll(results.get(0).keySet());
            }

            // Формируем строки для таблицы
            List<List<Object>> rows = new ArrayList<>();
            for (Map<String, Object> result : results) {
                List<Object> row = new ArrayList<>();
                for (String column : columns) {
                    row.add(result.get(column));
                }
                rows.add(row);
            }

            // Возвращаем результат
            response.put("columns", columns);
            response.put("rows", rows);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Возвращаем ошибку, если SQL-запрос некорректен
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}


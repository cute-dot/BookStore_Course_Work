package com.example.bookstore_course_work.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableDataResponse {

    private String tableName;  // Имя таблицы
    private List<Map<String, Object>> data;

}
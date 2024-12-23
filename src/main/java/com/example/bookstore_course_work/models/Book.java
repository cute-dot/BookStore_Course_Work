package com.example.bookstore_course_work.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    private String bookTitle;
    private int yearOfPublication;
    private int numberOfPages;
    private String publishingHouse;
    private int publishingHouseId;
    private int supplyId;
    private BigDecimal price;
}

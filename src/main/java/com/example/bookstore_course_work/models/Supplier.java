package com.example.bookstore_course_work.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {
    private int id;
    private String fullName;
    private String firstname;
    private String lastname;
    private String surname;
    private String documentInn;
    private String numberOfHouse;
    private String bankAccount;
    private int bankId;
    private String bankTittle;
    private int streetId;
    private String streetName;
    private int cityId;
    private String cityName;
}

package com.example.bookstore_course_work.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LegalCustomer {
    private int id;
    private String companyName;
    private String firstname;
    private String lastname;
    private String surname;
    private String numberOfHouse;
    private String bankAccount;
    private String documentInn;
    private int bankId;
    private String bankName;
    private int streetId;
    private String streetName;
    private int cityId;
    private String cityName;


}


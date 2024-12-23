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
public class Individual {
    private int id;
    private String firstname;
    private String lastname;
    private String surname;
    private String phoneNumber;
    private int passportSeries;
    private int passportNumber;
    private Date passportIssueDate;
    private String whoIssuedThePassport;
    private int cityId;
    private String cityName;
    private int streetId;
    private String streetName;
    private String bankAccount;
    private String numberOfHouse;
}

package com.example.bookstore_course_work.models;

import com.example.bookstore_course_work.interfaces.AuthorRep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author{
    private int id;
    private String firstname;
    private String lastname;
    private String surname;
    private String fullName;

}

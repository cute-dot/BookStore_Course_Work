package com.example.bookstore_course_work.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private int id;
    private String numberOfOrder;
    private Date dateOfOrder;

    private BigDecimal sumToPay;
    private Integer individualId;
    private Integer legalCustomerId;

    public void setIndividualId(Integer o) {
        this.individualId = o;
    }

    public void setLegalCustomerId(Integer o) {
        this.legalCustomerId = o;
    }
}

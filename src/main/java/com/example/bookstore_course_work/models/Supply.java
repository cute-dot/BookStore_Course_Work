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
public class Supply {
    private int id;
    private Date dateOfSupply;
    private BigDecimal purchasePrice;
    private int supplierId;
    private String fullSupplierName;

}

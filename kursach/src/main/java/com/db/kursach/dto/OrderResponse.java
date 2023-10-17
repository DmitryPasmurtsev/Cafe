package com.db.kursach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Date time;
    private Double price;
    private List<String> productsNames;
    private String description;
    private EmployeeDTO waiter;
}

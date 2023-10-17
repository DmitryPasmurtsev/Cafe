package com.db.kursach.dto.order;

import com.db.kursach.dto.EmployeeDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Date time;
    Double price;
    List<String> productsNames;
    String description;
    EmployeeDTO waiter;
}

package com.db.kursach.dto.order;

import com.db.kursach.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class OrderResponse {
    Long id;
    @DateTimeFormat
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    Date time;
    Double price;
    List<OrderProduct> products;
    String description;
    EmployeeDTO waiter;
}

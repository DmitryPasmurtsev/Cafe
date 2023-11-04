package com.db.kursach.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    Long id;
    String fullName;
    int experience;
    String phone;
    String email;
    Integer salary;
    LocalDate date;
    String linkToImage;
    PositionDTO position;
}

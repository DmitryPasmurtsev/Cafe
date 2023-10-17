package com.db.kursach.dto;

import com.db.kursach.models.Delivery;
import com.db.kursach.models.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {

    String fullName;
    int experience;
    String phone;
    String email;
    Integer salary;
    LocalDate date;
    //private byte[] image_bytes;
    PositionDTO position;
    UserDTO user;
}

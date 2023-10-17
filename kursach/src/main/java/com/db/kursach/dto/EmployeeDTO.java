package com.db.kursach.dto;

import com.db.kursach.models.Delivery;
import com.db.kursach.models.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class EmployeeDTO {

    private String fullName;
    private int experience;
    private String phone;
    private String email;

    private Integer salary;

    private LocalDate date;
    //private byte[] image_bytes;
    private PositionDTO position;
    private UserDTO user;
}

package com.db.kursach.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDTO {
    Long id;
    String name;
    String country;
    String city;
    String address;
    String phone;
}

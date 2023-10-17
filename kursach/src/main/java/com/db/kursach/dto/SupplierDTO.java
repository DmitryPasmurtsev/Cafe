package com.db.kursach.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDTO {
    private String name;
    private String country;
    private String city;
    private String address;
    private String phone;
}

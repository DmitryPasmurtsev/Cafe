package com.db.kursach.dto;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Product;
import com.db.kursach.models.Supplier;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryDTO {
    Long id;
    LocalDate date;
    Integer amount;
    ProductDTO product;
    EmployeeDTO employee;
    SupplierDTO supplier;
}

package com.db.kursach.dto;

import com.db.kursach.models.Employee;
import com.db.kursach.models.Product;
import com.db.kursach.models.Supplier;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DeliveryDTO {
    private LocalDate date;
    private Integer amount;
    private ProductDTO product;
    private EmployeeDTO employee;
    private SupplierDTO supplier;
}

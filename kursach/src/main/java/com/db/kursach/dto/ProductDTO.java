package com.db.kursach.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private Long amount;
    private Integer calories;
    private Integer unitWeight;
    private String description;
    private Float price;
}

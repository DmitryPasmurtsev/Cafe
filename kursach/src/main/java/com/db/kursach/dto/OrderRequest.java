package com.db.kursach.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private List<String> productsNames;
    private List<Integer> amounts;
    private String description;
}

package com.db.kursach.models.requestModels;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestModel {
    private List<String> productsNames;
    private List<Integer> amounts;
    private String description;
}

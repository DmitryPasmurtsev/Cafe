package com.db.kursach.dto.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    List<String> productsNames;
    List<Integer> amounts;
    String description;
}

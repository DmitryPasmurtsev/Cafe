package com.db.kursach.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "amount_in_stock")
    private Long amount= Long.valueOf(0);
    @Column(name = "calories")
    private Integer calories;
    @Column(name = "unit_weight")
    private Integer unitWeight;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_price")
    private Float price;

    @OneToMany(mappedBy = "id.product")
    @JsonIgnore
    private List<OrderComposition> orderComposition;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<Delivery> deliveries;
}

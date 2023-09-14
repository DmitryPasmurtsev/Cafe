package com.db.kursach.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;
    @Column(name = "company_name")
    private String name;
    @Column(name = "supplier_country")
    private String country;
    @Column(name = "supplier_city")
    private String city;
    @Column(name = "supplier_address")
    private String address;
    @Column(name = "supplier_phone_number")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "supplier")
    @JsonIgnore
    private List<Delivery> deliveries;

}

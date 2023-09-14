package com.db.kursach.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
public class OrderCompositionKey implements Serializable {
    @JsonIgnore
    private Order order;
    private Product product;
    public OrderCompositionKey(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    @ManyToOne()
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}

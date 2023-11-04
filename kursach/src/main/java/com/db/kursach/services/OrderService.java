package com.db.kursach.services;


import com.db.kursach.models.Order;

import com.db.kursach.models.User;

import java.util.List;

public interface OrderService {
    List<Order> listOrders();
    Order getOrderById(Long id);

    void createOrder(List<String> productsNames, List<Integer> amounts, String description, User userByPrincipal);

    void deleteOrder(Long id);
}

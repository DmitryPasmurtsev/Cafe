package com.db.kursach.controllers;



import com.db.kursach.models.Order;
import com.db.kursach.models.requestModels.OrderRequestModel;
import com.db.kursach.services.OrderService;
import com.db.kursach.services.impl.AuthServiceImpl;
import com.db.kursach.services.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final AuthServiceImpl authService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> orders(){
        List<Order> listOrders = orderService.listOrders();
        listOrders.sort(Comparator.comparing(Order::getTime).reversed());
        return ResponseEntity.ok(listOrders);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> orderInfo(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestModel orderRequestModel, Principal principal){
        orderService.createOrder(orderRequestModel.getProductsNames(),
                orderRequestModel.getAmounts(),
                orderRequestModel.getDescription(),
                authService.getUserByPrincipal(principal)
        );
        return ResponseEntity.ok("Добавлен новый заказ ");
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Удален заказ с id " +  id);
    }
}

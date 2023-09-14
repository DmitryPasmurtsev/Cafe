package com.db.kursach.controllers;



import com.db.kursach.models.Order;
import com.db.kursach.models.OrderComposition;
import com.db.kursach.models.OrderCompositionKey;
import com.db.kursach.models.requestModels.OrderRequestModel;
import com.db.kursach.services.AuthService;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.OrderService;
import com.db.kursach.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;

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

package com.db.kursach.controllers;



import com.db.kursach.dto.order.OrderResponse;
import com.db.kursach.models.Order;
import com.db.kursach.dto.order.OrderRequest;
import com.db.kursach.services.OrderService;
//import com.db.kursach.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;
    //private final AuthServiceImpl authService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> orders(){
        List<Order> listOrders = orderService.listOrders();
        listOrders.sort(Comparator.comparing(Order::getTime).reversed());
        List<OrderResponse> orderDTOS=listOrders.stream().map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOS);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponse> orderInfo(@PathVariable Long id){
        OrderResponse orderDTO=modelMapper.map(orderService.getOrderById(id), OrderResponse.class);
        return ResponseEntity.ok(orderDTO);
    }
    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequestModel, Principal principal){
        /*orderService.createOrder(orderRequestModel.getProductsNames(),
                orderRequestModel.getAmounts(),
                orderRequestModel.getDescription(),
                authService.getUserByPrincipal(principal)
        );*/
        return ResponseEntity.ok("Добавлен новый заказ ");
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Удален заказ с id " +  id);
    }
}

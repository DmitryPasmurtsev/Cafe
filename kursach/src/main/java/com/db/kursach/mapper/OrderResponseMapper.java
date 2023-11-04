package com.db.kursach.mapper;

import com.db.kursach.dto.order.OrderResponse;
import com.db.kursach.models.Order;
import com.db.kursach.models.Product;
import com.db.kursach.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderResponseMapper {
    private final ModelMapper mapper;
    private final OrderService orderService;
    public OrderResponse map(Order order){
        OrderResponse response=mapper.map(order, OrderResponse.class);
        List<Product> products=orderService.products(order);
        List<String> names=new ArrayList<>();
        for (Product product:products){
            names.add(product.getName());
        }
        response.setProductsNames(names);
        return response;
    }
}

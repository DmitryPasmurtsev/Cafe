package com.db.kursach.mapper;

import com.db.kursach.dto.order.OrderProduct;
import com.db.kursach.dto.order.OrderResponse;
import com.db.kursach.models.Order;
import com.db.kursach.models.OrderComposition;
import com.db.kursach.models.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class OrderResponseMapper {
    private final ModelMapper mapper;

    public OrderResponse map(Order order){
        OrderResponse response=mapper.map(order, OrderResponse.class);
        List<OrderProduct> products=products(order);
        response.setProducts(products);
        return response;

    }
    private List<OrderProduct> products(Order order) {
        List<OrderProduct> products = new ArrayList<>();
        List<OrderComposition> orderCompositions = order.getOrderComposition();
        for (OrderComposition composition : orderCompositions) {
            Product product = composition.getId().getProduct();
            OrderProduct orderProduct = mapper.map(product, OrderProduct.class);
            orderProduct.setProductAmount(composition.getAmount());
            products.add(orderProduct);
        }
        return products;
    }
}

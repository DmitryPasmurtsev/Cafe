package com.db.kursach.services.impl;



import com.db.kursach.exceptions.NotFoundException;
import com.db.kursach.models.*;
import com.db.kursach.repositories.OrderCompRepository;
import com.db.kursach.repositories.OrderRepository;
import com.db.kursach.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderCompRepository orderCompRepository;

    public List<Product> products(Order order){
        List<Product> products = new ArrayList<>();
        List<OrderComposition> orderCompositions = order.getOrderComposition();
        for (OrderComposition composition : orderCompositions) {

            Product product = composition.getId().getProduct();
            products.add(product);
        }
        return products;
    }

    public List<Order> listOrders(){
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id) {
        if (orderRepository.findById(id).isEmpty())
            throw new NotFoundException("Заказ отсутствует");
        return orderRepository.findById(id).get();
    }
    public Order addProductInOrder(Order order, Product product, Integer productAmount) {
        OrderComposition orderComposition = new OrderComposition();
        orderComposition.setOrder(order);
        orderComposition.setProduct(product);
        orderComposition.setAmount(productAmount);
        orderComposition.setId(new OrderCompositionKey(order, product));
        order.getOrderComposition().add(orderComposition);
        return order;
    }
    public void saveOrder(Order order) {
        orderRepository.save(order);
        orderCompRepository.saveAll(order.getOrderComposition());
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public void createOrder(List<String> productsNames, List<Integer> amounts, String description, User user) {
        Order order = new Order();
        order.setWaiter(employeeService.getEmployeeById(user.getEmployee().getId()));
        order.setTime(new Date());
        order.getTime().setHours(order.getTime().getHours()+3);
        order.setPrice(0.0);
        order.setOrderComposition(new ArrayList<>());
        for (int i = 0; i < productsNames.size(); i++) {
            order = addProductInOrder(order, productService.getProductByName(productsNames.get(i)), amounts.get(i));
        }
        order.setDescription(description);
        saveOrder(order);
    }
}


package com.example.operasales.controllers;

import com.example.operasales.domain.Order;
import com.example.operasales.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Collection<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @DeleteMapping
    public void delete(@RequestBody Order order){
        orderService.delete(order);
    }
}

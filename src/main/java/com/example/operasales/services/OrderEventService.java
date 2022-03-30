package com.example.operasales.services;

import com.example.operasales.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEventService {
    OrderRepository repository;

    @Autowired
    public OrderEventService(OrderRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}

package com.example.operasales.services;

import com.example.operasales.domain.Customer;
import com.example.operasales.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    CustomerRepository repository;

    @Autowired
    public EmailService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void send(String email, String message) {
        System.out.println(String.format("Отправим на почту %s сообщение: %s", email, message));
    }

    public void sendAll(String message) {
        for (Customer customer : repository.findAll()) {
            System.out.println(String.format("Отправим на почту %s сообщение: %s", customer.getEmail(), message));
        }
    }
}

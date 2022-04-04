package com.example.operasales.services;

import com.example.operasales.domain.Customer;
import com.example.operasales.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

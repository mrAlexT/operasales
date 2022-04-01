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

    public Customer save(String name, String email){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);

        return repository.save(customer);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}

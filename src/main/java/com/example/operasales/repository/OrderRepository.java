package com.example.operasales.repository;

import com.example.operasales.domain.OrderEvent;
import com.example.operasales.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEvent, String> {
    OrderEvent findByCustomerAndPlace(String customer, Place place);
}

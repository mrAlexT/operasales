package com.example.operasales.services;

import com.example.operasales.annotations.EmailAlerting;
import com.example.operasales.domain.Order;
import com.example.operasales.domain.Place;
import com.example.operasales.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class OrderService {
    OrderRepository repository;
    OperaEventService eventService;
    PlaceService placeService;

    public OrderService(OrderRepository repository, OperaEventService eventService, PlaceService placeService) {
        this.repository = repository;
        this.eventService = eventService;
        this.placeService = placeService;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @EmailAlerting("Покупка билета")
    @Transactional
    public Order createOrder(Order order) {
        for (Place place : order.getPlaces()) {
            placeService.checkPlace(place);
            place.setStatus(1);
            placeService.updatePlace(place);
        }

        Order result = repository.save(order);
        return result;
    }

    @EmailAlerting("Отмена билета")
    @Transactional
    public void delete(Order order) {
        for (Place place : order.getPlaces()) {
            place.setStatus(0);
            placeService.updatePlace(place);
        }
        repository.delete(order);
    }

    public Collection<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrder(String id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Покупка не найдена"));
    }
}

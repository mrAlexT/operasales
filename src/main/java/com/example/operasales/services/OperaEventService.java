package com.example.operasales.services;

import com.example.operasales.annotations.EmailAlerting;
import com.example.operasales.domain.*;
import com.example.operasales.repository.OperaEventRepository;
import com.example.operasales.repository.OrderRepository;
import com.example.operasales.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class OperaEventService {
    private OperaEventRepository repository;
    private PlaceRepository placeRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OperaEventService(OperaEventRepository repository, PlaceRepository placeRepository, OrderRepository orderRepository) {
        this.repository = repository;
        this.placeRepository = placeRepository;
        this.orderRepository = orderRepository;
    }

    @EmailAlerting("Новая премьера")
    public OperaEvent save(LocalDateTime time, Opera opera, int maxSeats) {
        OperaEvent operaEvent = new OperaEvent(time, opera, maxSeats);
        repository.save(operaEvent);
        return operaEvent;
    }

    public OperaEvent save(OperaEvent operaEvent) {
        repository.save(operaEvent);
        return operaEvent;
    }

    public Collection<OperaEvent> getOperaEvents() {
        return repository.findAll();
    }

    public OperaEvent findById(String eventId) {
        return repository.findById(eventId).get();
    }

    public OperaEvent findByOpera(Opera opera) {
        return repository.findByOpera(opera);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    /**
     * Бронирование места
     *
     * @param operaEvent Мероприятие
     * @param number     Номер места
     * @return
     */
    private Place setBookEvent(OperaEvent operaEvent, int number) {
        if (number > operaEvent.getMaxSeats()) {
            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
        }

        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);

        if (place != null) {
            if (place.getStatus() == 1) {
                throw new RuntimeException("Место уже занято: " + number);
            }
            place.setStatus(1);
        } else {
            place = new Place();
            place.setOperaEvent(operaEvent);
            place.setNumber(number);
            place.setStatus(1);
        }

        return placeRepository.save(place);
    }

    /**
     * Отмена бронирования места
     *
     * @param operaEvent Мероприятие
     * @param number     Номер места
     * @return
     */
    private void cancelBookEvent(OperaEvent operaEvent, int number) {
        if (number > operaEvent.getMaxSeats()) {
            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
        }

        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);

        if (place == null) {
            throw new RuntimeException("Место ещё не бронировалось " + operaEvent.getMaxSeats());
        }

        placeRepository.deleteById(place.getId());
    }

    /**
     * Покупка билета на мероприятие
     *
     * @param event    Мероприятие
     * @param place    Номер места
     * @param customer Имя покупателя
     */
    @EmailAlerting("Покупка билета")
    @Transactional
    public void buyTicket(OperaEvent event, int place, Customer customer) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setCustomer(customer);
        orderEvent.setPlace(setBookEvent(event, place));

        orderRepository.save(orderEvent);
    }

    /**
     * Отмена покупки билета
     *
     * @param operaEvent Мероприятие
     * @param number     Номер места
     * @param customer   Имя покупателя
     */
    @EmailAlerting("Отмена покупки билета")
    @Transactional
    public void cancelBuyTicket(OperaEvent operaEvent, int number, Customer customer) {
        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);
        if (place == null) {
            throw new IllegalArgumentException(String.format("Место %s ещё не занято", number));
        }

        OrderEvent orderEvent = orderRepository.findByCustomerAndPlace(customer, place);
        if (orderEvent == null) {
            throw new IllegalArgumentException(String.format("Вы не можете отменить покупку чужого места"));
        }

        cancelBookEvent(operaEvent, number);
        orderRepository.delete(orderEvent);
    }

}

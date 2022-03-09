package com.example.operasales.services;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import com.example.operasales.domain.Place;
import com.example.operasales.repository.jpa.JpaOperaEventRepository;
import com.example.operasales.repository.jpa.JpaPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class OperaEventService {
    private JpaOperaEventRepository repository;
    private JpaPlaceRepository placeRepository;

    @Autowired
    public OperaEventService(JpaOperaEventRepository repository, JpaPlaceRepository placeRepository) {
        this.repository = repository;
        this.placeRepository = placeRepository;
    }

    public OperaEvent createOperaEvent(LocalDateTime time, Opera opera, int maxSeats) {
        OperaEvent operaEvent = new OperaEvent(time, opera, maxSeats);
        repository.save(operaEvent);
        return operaEvent;
    }

    public OperaEvent createOperaEvent(OperaEvent operaEvent) {
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

    public void setBookEvent(OperaEvent operaEvent, int number) {
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

        placeRepository.save(place);
    }

    public void cancelBookEvent(OperaEvent operaEvent, int number) {
        if (number > operaEvent.getMaxSeats()) {
            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
        }

        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);

        if (place == null) {
            throw new RuntimeException("Место ещё не бронировалось " + operaEvent.getMaxSeats());
        }

        placeRepository.deleteById(place.getId());
    }

}

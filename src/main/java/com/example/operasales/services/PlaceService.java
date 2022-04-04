package com.example.operasales.services;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.domain.Place;
import com.example.operasales.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceService {

    private PlaceRepository repository;

    @Autowired
    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void updatePlace(Place place) {
        repository.save(place);
    }

    public Place findByOperaEventAndNumber(OperaEvent operaEvent, int number) {
        Place place = repository.findByOperaEventAndNumber(operaEvent, number).orElseThrow(() -> new IllegalArgumentException("Место для мероприятия не зарегистрировано"));
        return place;
    }

    public void checkPlace(Place place) {
        Place checkPlace = repository.findById(place.getId()).orElseThrow(() -> new IllegalArgumentException("Место для мероприятия не зарегистрировано"));
        if (checkPlace.getStatus() == 1) {
            throw new IllegalArgumentException("Место уже занято");
        }
    }
}

package com.example.operasales.services;

import com.example.operasales.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class OperaEventPlaceService {

    private PlaceRepository repository;

    public OperaEventPlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}

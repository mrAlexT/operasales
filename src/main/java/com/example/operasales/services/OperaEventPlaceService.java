package com.example.operasales.services;

import com.example.operasales.repository.jpa.JpaPlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class OperaEventPlaceService {

    private JpaPlaceRepository repository;

    public OperaEventPlaceService(JpaPlaceRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}

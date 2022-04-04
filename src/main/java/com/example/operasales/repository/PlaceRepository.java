package com.example.operasales.repository;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, String> {
    Optional<Place> findByOperaEventAndNumber(OperaEvent operaEvent, int number);
}

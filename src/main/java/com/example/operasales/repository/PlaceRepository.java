package com.example.operasales.repository;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, String> {
    Place findByOperaEventAndNumber(OperaEvent operaEvent, int number);
}

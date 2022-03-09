package com.example.operasales.repository.jpa;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceRepository extends JpaRepository<Place, String> {
    Place findByOperaEventAndNumber(OperaEvent operaEvent, int number);
}

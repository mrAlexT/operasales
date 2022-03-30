package com.example.operasales.repository;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperaEventRepository extends JpaRepository<OperaEvent, String> {
    OperaEvent findByOpera(Opera opera);
}

package com.example.operasales.repository.jpa;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOperaEventRepository extends JpaRepository<OperaEvent, String> {
    OperaEvent findByOpera(Opera opera);
}

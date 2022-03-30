package com.example.operasales.repository;

import com.example.operasales.domain.Opera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperaRepository extends JpaRepository<Opera, String> {

    List<Opera> findByName(String name);
}

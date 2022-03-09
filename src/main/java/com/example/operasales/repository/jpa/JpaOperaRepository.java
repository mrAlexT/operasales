package com.example.operasales.repository.jpa;

import com.example.operasales.domain.Opera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface JpaOperaRepository extends JpaRepository<Opera, String> {

    List<Opera> findByName(String name);
}

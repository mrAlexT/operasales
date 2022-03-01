package com.example.operasales.repository;

import com.example.operasales.domain.Opera;
import com.example.operasales.repository.interfaces.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OperaRepository implements CommonRepository<Opera> {
    Map<String, Opera> operas = new HashMap<>();

    @Override
    public Opera save(Opera domain) {
        Opera opera = operas.get(domain.getId());

        if (opera != null) {
            opera.setName(domain.getName());
            opera.setDesc(domain.getDesc());
            opera.setAgeCategory(domain.getAgeCategory());
            domain = opera;
        }

        operas.put(domain.getId(), domain);
        return domain;
    }

    @Override
    public void save(Collection<Opera> domains) {
        domains.forEach(this::save);
    }

    @Override
    public void delete(Opera domain) {
        operas.remove(domain.getId());
    }

    @Override
    public Opera findById(String id) {
        return operas.get(id);
    }

    @Override
    public Collection<Opera> findAll() {
        return new ArrayList<Opera>(operas.values());
    }

}

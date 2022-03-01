package com.example.operasales.services;

import com.example.operasales.domain.Opera;
import com.example.operasales.repository.interfaces.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OperaService {
    private CommonRepository<Opera> repository;

    @Autowired
    public OperaService(CommonRepository<Opera> repository) {
        this.repository = repository;
    }

    public Opera createOpera(String name, String desc, int ageCategory) {
        Opera opera = new Opera(name, desc, ageCategory);
        return repository.save(opera);
    }

    public Opera createOpera(Opera opera) {
        return repository.save(opera);
    }

    public Opera getOpera(String id) {
        return repository.findById(id);
    }

    public void deleteOpera(Opera opera) {
        repository.delete(opera);
    }

    public Collection<Opera> getOperas() {
        return repository.findAll();
    }

}

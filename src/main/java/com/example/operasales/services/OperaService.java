package com.example.operasales.services;

import com.example.operasales.domain.Opera;
import com.example.operasales.repository.OperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OperaService {
    private OperaRepository repository;


    @Autowired
    public OperaService(OperaRepository repository) {
        this.repository = repository;
    }

    public Opera save(String name, String desc, int ageCategory) {
        Opera opera = new Opera(name, desc, ageCategory);
        return repository.save(opera);
    }

    public Opera save(Opera opera) {
        Opera result = repository.save(opera);
        return result;
    }

    public Opera getOpera(String id){
        Optional<Opera> opera = repository.findById(id);
        if (opera.isPresent()){
            return opera.get();
        }
        return null;
    }

    public Opera getOperaByName(String name){
        List<Opera> operas =  repository.findByName(name);
        Optional<Opera> opera = operas.stream().findAny();

        if (opera.isPresent()){
            return opera.get();
        }

        return null;
    }

    public void deleteOpera(Opera opera) {
        repository.delete(opera);
    }

    public Collection<Opera> getOperas() {
        return (Collection<Opera>) repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}

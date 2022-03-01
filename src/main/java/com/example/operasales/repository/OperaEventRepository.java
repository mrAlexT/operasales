package com.example.operasales.repository;

import com.example.operasales.domain.OperaEvent;
import com.example.operasales.repository.interfaces.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OperaEventRepository implements CommonRepository<OperaEvent> {
    private Map<String, OperaEvent> events = new HashMap<>();

    @Override
    public OperaEvent save(OperaEvent domain) {
        OperaEvent event = events.get(domain.getId());

        if (event != null) {
            event.setTime(domain.getTime());
            event.setOpera(domain.getOpera());
            event.setTime(domain.getTime());
            event.setMaxSeats(domain.getMaxSeats());
            event.setSeats(domain.getSeats());
            domain = event;
        }

        events.put(domain.getId(), domain);
        return domain;
    }

    @Override
    public void save(Collection<OperaEvent> domains) {
        domains.forEach(this::save);
    }

    @Override
    public void delete(OperaEvent domain) {
        events.remove(domain.getId());
    }

    @Override
    public OperaEvent findById(String id) {
        return events.get(id);
    }

    @Override
    public Collection<OperaEvent> findAll() {
        return new ArrayList<OperaEvent>(events.values());
    }
}

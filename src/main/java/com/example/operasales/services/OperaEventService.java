package com.example.operasales.services;

import com.example.operasales.domain.Opera;
import com.example.operasales.domain.OperaEvent;
import com.example.operasales.repository.interfaces.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class OperaEventService {
    private CommonRepository<OperaEvent> repository;

    @Autowired
    public OperaEventService(CommonRepository<OperaEvent> repository) {
        this.repository = repository;
    }

    public OperaEvent createOperaEvent(LocalDateTime time, Opera opera, int maxSeats) {
        OperaEvent operaEvent = new OperaEvent(time, opera, maxSeats);
        repository.save(operaEvent);

        return operaEvent;
    }

    public OperaEvent createOperaEvent(OperaEvent operaEvent) {
        repository.save(operaEvent);
        return operaEvent;
    }

    public Collection<OperaEvent> getOperaEvents() {
        return repository.findAll();
    }

    public void setBookEvent(OperaEvent operaEvent, int place){
        if (place > operaEvent.getSeats().length) {
            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
        }

        if (operaEvent.getSeats()[place] == 1) {
            throw new RuntimeException("Место уже занято: " + place);
        }

        operaEvent.getSeats()[place] = 1;
    }

    public void cancelBookEvent(OperaEvent operaEvent, int place){
        operaEvent.getSeats()[place] = 0;
    }

    public int[] getSeats(OperaEvent operaEvent){
        return operaEvent.getSeats();
    }
}

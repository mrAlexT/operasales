package com.example.operasales.services;

import com.example.operasales.annotations.EmailAlerting;
import com.example.operasales.domain.*;
import com.example.operasales.repository.OperaEventRepository;
import com.example.operasales.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class OperaEventService {
    private OperaEventRepository repository;
    private PlaceRepository placeRepository;

    @Autowired
    public OperaEventService(OperaEventRepository repository, PlaceRepository placeRepository) {
        this.repository = repository;
        this.placeRepository = placeRepository;
    }

    @EmailAlerting("Новая премьера")
    public OperaEvent createOperaEvent(LocalDateTime time, Opera opera, int maxSeats) {
        OperaEvent operaEvent = new OperaEvent(time, opera, maxSeats);
        return createOperaEvent(operaEvent);
    }

    @Transactional
    public OperaEvent createOperaEvent(OperaEvent operaEvent) {
        OperaEvent result = repository.save(operaEvent);
        Place place;

        for (int i = 1; i < operaEvent.getMaxSeats(); i++) {
            place = new Place();
            place.setNumber(i);
            place.setStatus(0);
            place.setOperaEvent(result);
            placeRepository.save(place);
        }

        return result;
    }

    @Transactional
    public OperaEvent updateOperaEvent(OperaEvent operaEvent) {
        OperaEvent result = repository.save(operaEvent);
        return result;
    }

    public Collection<OperaEvent> getOperaEvents() {
        return repository.findAll();
    }

    public OperaEvent findById(String eventId) {
        return repository.findById(eventId).get();
    }

    public OperaEvent findByOpera(Opera opera) {
        return repository.findByOpera(opera);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void delete(OperaEvent operaEvent){
        repository.delete(operaEvent);
    }

//    /**
//     * Бронирование места
//     *
//     * @param operaEvent Мероприятие
//     * @param number     Номер места
//     * @return
//     */
//     Place setBookEvent(OperaEvent operaEvent, int number) {
//        if (number > operaEvent.getMaxSeats()) {
//            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
//        }
//
//        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);
//
//        if (place != null) {
//            if (place.getStatus() == 1) {
//                throw new RuntimeException("Место уже занято: " + number);
//            }
//            place.setStatus(1);
//        } else {
//            place = new Place();
//
//
//            place.setNumber(number);
//            place.setStatus(1);
//        }
//
//        return placeRepository.save(place);
//    }
//
//    /**
//     * Отмена бронирования места
//     *
//     * @param operaEvent Мероприятие
//     * @param number     Номер места
//     * @return
//     */
//    void cancelBookEvent(OperaEvent operaEvent, int number) {
//        if (number > operaEvent.getMaxSeats()) {
//            throw new RuntimeException("Количество мест не более " + operaEvent.getMaxSeats());
//        }
//
//        Place place = placeRepository.findByOperaEventAndNumber(operaEvent, number);
//
//        if (place == null) {
//            throw new RuntimeException("Место ещё не бронировалось " + operaEvent.getMaxSeats());
//        }
//
//        placeRepository.deleteById(place.getId());
//    }

}

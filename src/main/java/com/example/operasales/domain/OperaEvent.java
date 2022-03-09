package com.example.operasales.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class OperaEvent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private LocalDateTime time;
    @OneToOne
    @JoinColumn(name = "opera_id", referencedColumnName = "id")
    private Opera opera;
    private int maxSeats;

    public OperaEvent(LocalDateTime time, Opera opera, int maxSeats) {
        this.id = UUID.randomUUID().toString();
        this.time = time;
        this.opera = opera;
        this.maxSeats = maxSeats;
    }

    public OperaEvent() {
    }

    @Override
    public String toString() {
        return "OperaEvent{" +
                "time=" + time +
                ", opera=" + opera.getName() +
                ", maxSeats=" + maxSeats +
                '}';
    }
}

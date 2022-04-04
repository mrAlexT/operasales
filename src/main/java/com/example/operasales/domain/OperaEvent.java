package com.example.operasales.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "OPERA_EVENT")
public class OperaEvent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private LocalDateTime time;
    @ManyToOne
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Opera getOpera() {
        return opera;
    }

    public void setOpera(Opera opera) {
        this.opera = opera;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }
}

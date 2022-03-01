package com.example.operasales.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OperaEvent {
    @NotNull
    private String id;
    private LocalDateTime time;
    private Opera opera;
    private int maxSeats;
    private int[] seats;

    public OperaEvent(LocalDateTime time, Opera opera, int maxSeats) {
        this.id = UUID.randomUUID().toString();
        this.time = time;
        this.opera = opera;
        this.maxSeats = maxSeats;
        this.seats = new int[maxSeats];
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

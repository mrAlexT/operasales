package com.example.operasales.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Place {
    private String id;
    private int number;
    private int status;
    private OperaEvent operaEvent;
    private int buyer;

    public Place(int number, int status, OperaEvent operaEvent, int buyer) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.status = status;
        this.operaEvent = operaEvent;
        this.buyer = buyer;
    }

}

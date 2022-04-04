package com.example.operasales.domain;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Check(constraints = "status in (0,1)")
public class Place {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private int number;
    private int status;

    @ManyToOne
    @JoinColumn(name = "opera_event_id")
    private OperaEvent operaEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OperaEvent getOperaEvent() {
        return operaEvent;
    }

    public void setOperaEvent(OperaEvent operaEvent) {
        this.operaEvent = operaEvent;
    }
}

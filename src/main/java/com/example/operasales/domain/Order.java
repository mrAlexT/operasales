package com.example.operasales.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDER_EVENT")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private OperaEvent operaEvent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    List<Place> places;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OperaEvent getOperaEvent() {
        return operaEvent;
    }

    public void setOperaEvent(OperaEvent operaEvent) {
        this.operaEvent = operaEvent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}

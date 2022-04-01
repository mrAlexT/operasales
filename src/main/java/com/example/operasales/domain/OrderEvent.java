package com.example.operasales.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class OrderEvent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

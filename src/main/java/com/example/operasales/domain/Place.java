package com.example.operasales.domain;

import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
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

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    OrderEvent orderEvent;
}

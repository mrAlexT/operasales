package com.example.operasales.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Opera {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "age_category")
    private int ageCategory;

    public Opera(String name, String description, int ageCategory) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
    }

    public Opera() {
    }

    @Override
    public String toString() {
        return "Opera{" +
                "name='" + name +
                ", desc='" + description +
                '}';
    }
}

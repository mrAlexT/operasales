package com.example.operasales.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }
}

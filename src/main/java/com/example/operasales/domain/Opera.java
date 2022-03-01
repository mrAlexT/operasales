package com.example.operasales.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class Opera {
    @NotNull
    private String id;
    private  String name;
    private  String desc;
    private int ageCategory;

    public Opera(String name, String desc, int ageCategory) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.desc = desc;
        this.ageCategory = ageCategory;
    }

    @Override
    public String toString() {
        return "Opera{" +
                "name='" + name +
                ", desc='" + desc +
                '}';
    }
}

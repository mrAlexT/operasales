package com.example.operasales.dto;

import lombok.Data;

@Data
public class ExceptionDto {
    String message;

    public ExceptionDto(String message) {
        this.message = message;
    }
}

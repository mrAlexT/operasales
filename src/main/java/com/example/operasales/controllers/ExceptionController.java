package com.example.operasales.controllers;

import com.example.operasales.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ExceptionDto handleException(Exception exception) {
        return new ExceptionDto(exception.getMessage());
    }
}

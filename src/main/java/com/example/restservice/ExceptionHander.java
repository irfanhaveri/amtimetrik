package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHander {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptions(Exception e){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

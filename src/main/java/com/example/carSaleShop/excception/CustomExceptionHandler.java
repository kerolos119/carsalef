package com.example.carSaleShop.excception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler {
    @Autowired
    MessageSource messageSource;
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customExceptionHandler(CustomException ex){
        String message =messageSource.getMessage(ex.getMessage(),null , Locale.ENGLISH);
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(message,ex.getStatus(),ex.getStatus().value(), LocalDateTime.now()).getStatus());
    }
}

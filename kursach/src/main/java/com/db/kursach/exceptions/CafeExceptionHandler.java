package com.db.kursach.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CafeExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCardNotFoundException(NotFoundException notFoundException){
        ExceptionResponse response=
                new ExceptionResponse(
                        notFoundException.getMessage(), LocalDateTime.now(),
                        notFoundException.getCause(), HttpStatus.NOT_FOUND
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
}

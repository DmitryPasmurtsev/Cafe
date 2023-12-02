package com.db.kursach.exceptions.handler;

import com.db.kursach.dto.exception.ExceptionResponse;
import com.db.kursach.exceptions.AuthException;
import com.db.kursach.exceptions.NotCreatedException;
import com.db.kursach.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.NOT_FOUND,
                        notFoundException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
    @ExceptionHandler(value = {NotCreatedException.class})
    public ResponseEntity<Object> handleNotCreatedException(NotCreatedException notCreatedException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.BAD_REQUEST,
                        notCreatedException.getMessage(),notCreatedException.getField()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<Object> handleAuthException(AuthException authException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.BAD_REQUEST,
                        authException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
}

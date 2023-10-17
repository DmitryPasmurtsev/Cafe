package com.db.kursach.exceptions;

public class NotCreatedException extends RuntimeException{
    public NotCreatedException(String message) {
        super(message);
    }

    public NotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}

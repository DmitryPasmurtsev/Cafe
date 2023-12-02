package com.db.kursach.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotCreatedException extends RuntimeException{
    private String field;
    public NotCreatedException(String message,String field) {

        super(message);
        this.field=field;
    }

    public NotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.db.kursach.dto.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponse {
    final HttpStatus status;
    final String message;
    String field;

    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this.message=message;
        this.status=httpStatus;
    }
}

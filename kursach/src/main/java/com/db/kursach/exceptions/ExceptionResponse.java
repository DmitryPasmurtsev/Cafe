package com.db.kursach.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ExceptionResponse {
     final String message;
     final LocalDateTime dateTime;
     final Throwable cause;
     final HttpStatus status;

}

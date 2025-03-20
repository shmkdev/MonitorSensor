package org.test.monitorsensors.util.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.monitorsensors.util.exception.AuthError;
import org.test.monitorsensors.util.exception.DataNotFoundException;

@RestControllerAdvice
public class DataExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<AuthError> notValid(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(AuthError.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<AuthError> notFoundException(DataNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(AuthError.builder()
                        .message(exception.getMessage())
                        .build());
    }
}

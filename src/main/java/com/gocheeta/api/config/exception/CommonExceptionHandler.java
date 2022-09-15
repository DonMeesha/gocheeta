package com.gocheeta.api.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map> exceptionIllegalArgumentHandler(IllegalArgumentException exception) {
        Map responseException = new HashMap();
        responseException.put("statusCode", HttpStatus.BAD_REQUEST);
        responseException.put("message", exception.getMessage());
        return new ResponseEntity<Map>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map> exceptionNoSuchExceptionHandler(NoSuchElementException exception) {
        Map responseException = new HashMap();
        responseException.put("statusCode", HttpStatus.NOT_FOUND);
        responseException.put("message", exception.getMessage());
        return new ResponseEntity<Map>(responseException, HttpStatus.NOT_FOUND);
    }


}

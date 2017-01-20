package com.kawnayeen.logger.controller;

import com.kawnayeen.logger.model.service.exception.*;
import com.kawnayeen.logger.security.token.auth.ratelimit.RateLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by kawnayeen on 1/13/17.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ApplicationNotFoundException.class)
    public ResponseEntity<Map<String, Object>> applicationNotFound(ApplicationNotFoundException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidLogException.class)
    public ResponseEntity<Map<String, Object>> invalidLog(InvalidLogException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidApplicationException.class)
    public ResponseEntity<Map<String, Object>> invalidApplication(InvalidApplicationException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RateLimitExceededException.class)
    public ResponseEntity<Map<String, Object>> rateLimitExceeded(RateLimitExceededException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = LogNotFoundException.class)
    public ResponseEntity<Map<String, Object>> logNotFound(LogNotFoundException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> accountNotFound(AccountNotFoundException e) {
        return new ResponseEntity<>(Collections.singletonMap("response", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

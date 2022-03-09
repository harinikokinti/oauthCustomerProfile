package com.harini.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Map<String,String>> handleAuthenticationException(BaseException baseException) {

        Map<String,String> errorMap = Map.of(
                "message", baseException.getMessage(),
                "errorCode", baseException.getErrorCode()
        );  // Map.of is a helper method of Map
        ResponseEntity<Map<String,String>> responseEntity = new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
        return responseEntity;

    }
}

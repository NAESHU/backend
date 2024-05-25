package com.naeshu.naeshubackend.common;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> handle(UnAuthorizedException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage()),
                HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value())
        );
    }
}

package com.example.intcomex.application.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage errorBinder(BindException ex) {
        return ErrorMessage.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .result(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getAllErrors().stream().map(BinderMessage::from)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage errorData(DataIntegrityViolationException ex, HttpHeaders headers, HttpStatus status) {
        return ErrorMessage.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .result(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

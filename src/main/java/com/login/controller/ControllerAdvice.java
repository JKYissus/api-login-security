package com.login.controller;

import com.login.dto.ErrorDTO;
import com.login.exceptions.RequestException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(@NotNull RequestException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().meta(ex.getMeta()).build(), ex.getStatus());
    }
}

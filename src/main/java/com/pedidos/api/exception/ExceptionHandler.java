package com.pedidos.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<StandardError> itemNotFoundExceptionHandler (ItemNotFoundException erro){
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                erro.getMessage(),
                "Item não encontrado"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}

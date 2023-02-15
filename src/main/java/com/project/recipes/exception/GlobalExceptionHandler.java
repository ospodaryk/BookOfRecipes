package com.project.recipes.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler { // consider extending ResponseEntityExceptionHandler
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // todo
        return ResponseEntity.badRequest().body(ex.getBindingResult().toString());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNullEntityReferenceException(NullEntityReferenceException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        // todo: add logging
        logger.error("EntityNotFoundException:" + ex.getMessage());
        ErrorResponse response = new ErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntityNotCreatedException(EntitiyNotCreatedException ex) {
        // todo: add logging
        logger.error("EntitiyNotCreatedException:" + ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    // todo: add more handlers
}

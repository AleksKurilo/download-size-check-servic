package com.oracle.DownloadSizeCheckService.controller;

import com.oracle.DownloadSizeCheckService.exception.HeaderValueNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HeaderValueNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(HeaderValueNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}

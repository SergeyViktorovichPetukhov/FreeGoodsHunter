package com.sergo.wic.controller;


import com.sergo.wic.dto.Response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
//        return new Response(false, 1, ex.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList()));
//    }

//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    @ExceptionHandler(NullPointerException.class)
//    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
//        return new Response(false, 1, ex.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList()));
//    }
}

package com.sergo.wic.exception;


import com.sergo.wic.dto.Response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ImageNotUploadedException.class)
    public Response handleImageNotUploadedExceptions(ImageNotUploadedException ex) {
        return new Response(false, 1, ex.getMessage());
    }

    @ExceptionHandler(NoSuchUserException.class)
    public Response handleNoSuchUserExceptions(NoSuchUserException ex) {
        return new Response(false, 1, ex.getMessage());
    }

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

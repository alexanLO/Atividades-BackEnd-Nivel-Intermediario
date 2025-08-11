package com.alexan.spring_ecossistema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alexan.spring_ecossistema.exceptions.response.ApiErrorResponse;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
        error.addValidationErrors(ex.getFieldErrors());
        return ResponseEntity.badRequest().body(error);
    }

}

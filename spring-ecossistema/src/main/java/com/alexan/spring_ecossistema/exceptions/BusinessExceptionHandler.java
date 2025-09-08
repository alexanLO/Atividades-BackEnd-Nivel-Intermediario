package com.alexan.spring_ecossistema.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alexan.spring_ecossistema.exceptions.response.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
        error.addValidationErrors(ex.getFieldErrors());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        
        HttpStatus status = HttpStatus.resolve(Integer.parseInt(ex.getErroCode()));
        
        if (status == null) {
            status = HttpStatus.BAD_REQUEST; // fallback se o código não existir no HttpStatus
        }

        var apiErro = new ApiErrorResponse(status, ex);
        apiErro.setMessage(ex.getMessage());
        return buildResponseEntity(apiErro, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError, Exception ex) {
        log.error("Excecao sendo capturada, APIErrorCode: {}, Menssagem: {}, Excecao ", apiError.getCodeError(),
                apiError.getMessage(), ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}

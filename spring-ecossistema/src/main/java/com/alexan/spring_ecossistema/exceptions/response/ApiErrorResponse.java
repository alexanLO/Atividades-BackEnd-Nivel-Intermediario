package com.alexan.spring_ecossistema.exceptions.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.validation.ConstraintViolation;

@JsonTypeName("apierror")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ApiErrorResponse {

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy HH:mm:ss")
    private LocalDateTime timeStamp;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("codigoErro")
    private Integer codeError;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("mensagemDetalhada")
    private String detailMessage;

    @JsonProperty("subErros")
    private List<ApiSubErrorResponse> subErrors;

    private ApiErrorResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
        this.codeError = status.value();
    }

    public ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.codeError = status.value();
        this.message = "Erro inesperado";
        this.detailMessage = ex.getMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.codeError = status.value();
        this.message = message;
        this.detailMessage = ex.getMessage();
    }

    private void addSubError(ApiSubErrorResponse subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    public void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationErrorResponse(object, field, rejectedValue, message));
    }

    private void addValidationError(String code, String message) {
        addSubError(new ApiValidationErrorResponse(code, message));
    }

    public void addErrorBusiness(String code, String message) {
        addSubError(new SubErrorBusiness(code, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Método utilitário para adicionar o erro de ConstraintViolation. Geralmente,
     * quando uma validação @Validated falha.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationError(Set<ConstraintViolation> constraintViolation) {
        constraintViolation.forEach(this::addValidationError);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCodeError() {
        return codeError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public List<ApiSubErrorResponse> getSubErrors() {
        return subErrors;
    }

}

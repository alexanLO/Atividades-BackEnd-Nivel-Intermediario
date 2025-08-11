package com.alexan.spring_ecossistema.exceptions.response;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Especifico para validacao de API
 */
@JsonTypeName("validacao")
public class ApiValidationErrorResponse implements ApiSubErrorResponse {

    private final String object;
    private final String message;
    private String field;
    private Object rejectedValue;

    public ApiValidationErrorResponse(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public ApiValidationErrorResponse(final String object, final String message) {
        this.object = object;
        this.message = message;
    }


    public String getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

}

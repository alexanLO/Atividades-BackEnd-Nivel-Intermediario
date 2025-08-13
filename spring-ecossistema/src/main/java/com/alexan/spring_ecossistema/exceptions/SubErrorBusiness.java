package com.alexan.spring_ecossistema.exceptions;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Especifico para validacao de API
 */
@JsonTypeName("negocio")
public class SubErrorBusiness implements ApiSubErrorResponse {

    private final String code;
    private final String message;

    SubErrorBusiness(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

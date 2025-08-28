package com.alexan.spring_ecossistema.exceptions.response;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Especifico para validacao de API
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonTypeName("validacao")
public class ApiValidationErrorResponse implements ApiSubErrorResponse {

    private final String object;
    private final String message;
    private String field;
    private Object rejectedValue;
}

package com.alexan.spring_ecossistema.exceptions;

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
@JsonTypeName("negocio")
public class SubErrorBusiness implements ApiSubErrorResponse {

    private final String code;
    private final String message;
}

package com.alexan.spring_ecossistema.exceptions.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Classe base para criar erros diversos
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
                @JsonSubTypes.Type(value = ApiValidationErrorResponse.class, name = "validação"),
                @JsonSubTypes.Type(value = ApiSubErrorResponse.class, name = "negocio")
})
public interface ApiSubErrorResponse {
}

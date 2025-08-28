package com.alexan.spring_ecossistema.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String erroCode;
    private final String erroMsg;

    public BusinessException(Integer erroCode, String erroMsg) {
        super(erroMsg);
        this.erroCode = Integer.toString(erroCode);
        this.erroMsg = erroMsg;
    }

    public BusinessException(String erroCode, String erroMsg) {
        super(erroMsg);
        this.erroCode = erroCode;
        this.erroMsg = erroMsg;
    }
}
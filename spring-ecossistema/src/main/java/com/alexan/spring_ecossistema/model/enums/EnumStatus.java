package com.alexan.spring_ecossistema.model.enums;

public enum EnumStatus {

    ATIVO("ativo"),
    INATIVO("inativo");

    String valor;

    private EnumStatus(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static EnumStatus fromString(String valor) {
        try {
            return EnumStatus.valueOf(valor.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Status inválido: " + valor + "Use 'Ativo' ou 'Inativo'.");
        }
    }
}

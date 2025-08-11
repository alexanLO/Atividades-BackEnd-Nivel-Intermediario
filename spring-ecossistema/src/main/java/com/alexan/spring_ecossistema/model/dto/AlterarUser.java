package com.alexan.spring_ecossistema.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlterarUser {

    @NotBlank(message = "Nome e obrigatorio.")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String name;
    @Email(message = "Por favor, forneça um endereço de e-mail válido")
    @NotBlank(message = "Email e obrigatorio.")
    private String email;

    public AlterarUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

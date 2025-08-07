package com.alexan.spring_ecossistema.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class AlterarUser {

    @NotBlank(message = "Nome e obrigatorio.")
    private String name;
    @NotBlank(message = "Email e obrigatorio.")
    private String email;
    
    public AlterarUser(@NotBlank(message = "Nome e obrigatorio.") String name,
            @NotBlank(message = "Email e obrigatorio.") String email) {
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

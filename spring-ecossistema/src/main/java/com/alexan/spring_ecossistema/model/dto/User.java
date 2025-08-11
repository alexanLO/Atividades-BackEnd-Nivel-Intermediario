package com.alexan.spring_ecossistema.model.dto;

import java.time.LocalDateTime;

import com.alexan.spring_ecossistema.model.enums.EnumStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;

public class User {

    private Long id;
    @NotBlank(message = "Nome e obrigatorio.")
    private String name;
    @NotBlank(message = "Email e obrigatorio.")
    private String email;
    private EnumStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    public User(Long id, String name, String email, EnumStatus status, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
 
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
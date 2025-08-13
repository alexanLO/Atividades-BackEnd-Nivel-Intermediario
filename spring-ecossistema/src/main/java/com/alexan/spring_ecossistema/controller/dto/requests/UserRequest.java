package com.alexan.spring_ecossistema.controller.dto.requests;

import java.time.LocalDateTime;

import com.alexan.spring_ecossistema.model.enums.EnumStatus;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;
import com.alexan.spring_ecossistema.validator.annotations.ValidEmailCorp;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

    private Long id;
    @NotBlank(message = "Nome e obrigatorio.")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String name;
    @ValidEmailCorp
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$", message = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
    private String password;
    @NotNull(message = "Status é obrigatório")
    private EnumStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @NotNull(message = "Role é obrigatório")
    @Pattern(regexp = "ADMIN|USER", message = "Role deve ser ADMIN ou USER")
    private RoleEnum role;

    public UserRequest(Long id, String name, String email, String password, EnumStatus status,
            LocalDateTime creationDate, RoleEnum role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.creationDate = creationDate;
        this.role = role;
    }

    public UserRequest(String email, String password, RoleEnum role) {
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
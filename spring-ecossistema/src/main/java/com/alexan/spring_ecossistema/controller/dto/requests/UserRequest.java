package com.alexan.spring_ecossistema.controller.dto.requests;

import com.alexan.spring_ecossistema.model.enums.StatusEnum;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;
import com.alexan.spring_ecossistema.validator.annotations.ValidEmailCorp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Nome e obrigatorio.")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String fullName;

    @ValidEmailCorp
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$", message = "Senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
    private String password;

    @NotNull(message = "Role é obrigatório")
    private RoleEnum role;

    @NotNull(message = "Status é obrigatório")
    private StatusEnum status;
}
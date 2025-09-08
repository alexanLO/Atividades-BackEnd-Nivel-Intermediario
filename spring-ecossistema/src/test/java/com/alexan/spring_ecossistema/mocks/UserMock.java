package com.alexan.spring_ecossistema.mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.UserResponse;
import com.alexan.spring_ecossistema.model.LoginAttempt;
import com.alexan.spring_ecossistema.model.Profile;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;
import com.alexan.spring_ecossistema.model.enums.StatusEnum;
import com.alexan.spring_ecossistema.repository.entity.ProfileEntity;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.projections.UserSummaryProjection;

public class UserMock {

    // * Mensagens de erros */
    public static final String USER_EXIST = "Já existe um usuário com esse nome ou email";
    public static final String NOT_FOUND = "Usuario nao encontrado.";

    // * Dados validos, caso precise de outros dados implementar diretamente nos
    // * testes. */
    public static final UUID ID = UUID.randomUUID();
    public static final String FULL_NAME = "Nome Sobrenome UltimoNome";
    public static final String EMAIL = "nomeSobrenome@Empresa.com";
    public static final String PASSWORD = "senhaTeste1@";
    public static final RoleEnum ROLE_ADMIN = RoleEnum.ADMIN;
    public static final RoleEnum ROLE_USER = RoleEnum.USER;
    public static final StatusEnum ACTIVE_STATUS = StatusEnum.ATIVO;
    public static final StatusEnum INACTIVE_STATUS = StatusEnum.INATIVO;
    public static final StatusEnum LOCKED_STATUS = StatusEnum.BLOQUEADO;
    public static final StatusEnum EXPIRED_STATUS = StatusEnum.EXPIRADO;
    public static final LocalDateTime CREATE_AT = LocalDateTime.now();
    public static final Profile PROFILE = Profile.builder().build();
    public static final List<LoginAttempt> LOGIN_ATTEMPTS = new ArrayList<>();

    public User createUserFaker() {
        return User.builder()
                .id(ID)
                .fullName(FULL_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(ROLE_ADMIN)
                .status(ACTIVE_STATUS)
                .createAt(CREATE_AT)
                .profile(PROFILE)
                .loginAttempts(LOGIN_ATTEMPTS)
                .build();
    }

    public UserEntity createUserEntityFaker() {
        return UserEntity.builder()
                .id(ID)
                .fullName(FULL_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(ROLE_ADMIN)
                .status(ACTIVE_STATUS)
                .createAt(CREATE_AT)
                .profile(ProfileEntity.builder().build())
                .loginAttempts(new ArrayList<>())
                .build();
    }

    public UserSummaryProjection createUserSummaryFaker() {
        return new UserSummaryProjection() {
            @Override
            public UUID getId() {
                return ID;
            };

            public String getFullName() {
                return FULL_NAME;
            };

            public String getEmail() {
                return EMAIL;
            };

            public RoleEnum getRole() {
                return ROLE_ADMIN;
            };

            public StatusEnum getStatus() {
                return ACTIVE_STATUS;
            };

            public LocalDateTime getCreateAt() {
                return CREATE_AT;
            };
        };
    }

    public UserRequest createUserRequestFaker() {
        return UserRequest.builder()
        .fullName(FULL_NAME)
        .email(EMAIL)
        .password(PASSWORD)
        .role(ROLE_USER)
        .status(ACTIVE_STATUS)
        .build();
    }

    public UserResponse createUserResponseFaker(){
        return UserResponse.builder()
        .id(ID)
        .fullName(FULL_NAME)
        .email(EMAIL)
        .role(ROLE_ADMIN.name())
        .status(ACTIVE_STATUS.name())
        .createAt(CREATE_AT)
        .build();
    }
}

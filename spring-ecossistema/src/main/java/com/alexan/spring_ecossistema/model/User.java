package com.alexan.spring_ecossistema.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alexan.spring_ecossistema.model.enums.RoleEnum;
import com.alexan.spring_ecossistema.model.enums.StatusEnum;

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
public class User {

    private UUID id;

    private String fullName;

    private String email;

    private String password;

    private RoleEnum role;

    private StatusEnum status;

    private LocalDateTime createAt;

    private Profile profile;

    private List<LoginAttempt> loginAttempts;
}

package com.alexan.spring_ecossistema.model;

import java.time.LocalDateTime;

import com.alexan.spring_ecossistema.repository.entity.UserEntity;

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
public class LoginAttempt {

    private String id;

    private LocalDateTime attemptTime;

    // * Essa coluna é um boolean, mas como usamos oracle db ele não aceita true */
    // * ou false então deve se passar 0 para false e 1 para true */
    private String success;

    private UserEntity user;
}

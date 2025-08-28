package com.alexan.spring_ecossistema.controller.dto.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alexan.spring_ecossistema.repository.entity.LoginAttemptEntity;
import com.alexan.spring_ecossistema.repository.entity.ProfileEntity;

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
public class UserFullInfoResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String role;

    private String status;

    private LocalDateTime createAt;

    private ProfileEntity profile;

    private List<LoginAttemptEntity> loginAttempts;
}

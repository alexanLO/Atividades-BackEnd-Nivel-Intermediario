package com.alexan.spring_ecossistema.controller.dto.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
public class UserProfilAttemptsResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String role;

    private String status;

    private LocalDateTime createAt;

    private ProfileResponse profile;

    private List<LoginAttemptResponse> loginAttempts;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileResponse {

        private String bio;

        private String avatarUrl;

        private LocalDate birthDate;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginAttemptResponse {

        private LocalDateTime attemptTime;

        private String success;

        private UserEntity user;
    }
}

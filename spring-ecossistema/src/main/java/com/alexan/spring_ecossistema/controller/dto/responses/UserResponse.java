package com.alexan.spring_ecossistema.controller.dto.responses;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class UserResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String role;

    private String status;

    private LocalDateTime createAt;
}
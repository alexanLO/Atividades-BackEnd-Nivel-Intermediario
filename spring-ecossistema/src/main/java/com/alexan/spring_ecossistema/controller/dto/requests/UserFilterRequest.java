package com.alexan.spring_ecossistema.controller.dto.requests;

import java.time.LocalDateTime;

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
public class UserFilterRequest {

    private String fullName;

    private String email;

    private String status;

    private LocalDateTime createBy;

    private LocalDateTime createUntil;
}

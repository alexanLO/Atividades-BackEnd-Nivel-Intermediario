package com.alexan.spring_ecossistema.repository.projections;

import java.time.LocalDateTime;
import java.util.UUID;

import com.alexan.spring_ecossistema.model.enums.StatusEnum;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;

public interface UserSummaryProjection {

    UUID getId();

    String getFullName();

    String getEmail();

    RoleEnum getRole();

    StatusEnum getStatus();

    LocalDateTime getCreateAt();
}

package com.alexan.spring_ecossistema.repository.projections;

import java.time.LocalDateTime;
import java.util.UUID;

import com.alexan.spring_ecossistema.model.enums.EnumStatus;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;

public interface UserSummaryProjection {

    UUID getId();

    String getFullName();

    String getEmail();

    RoleEnum getRole();

    EnumStatus getStatus();

    LocalDateTime getCreateAt();
}

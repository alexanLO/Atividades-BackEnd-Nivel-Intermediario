package com.alexan.spring_ecossistema.model;

import java.time.LocalDate;

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
public class Profile {

    private String id;

    private String bio;

    private String avatarUrl;

    private LocalDate birthDate;

    private UserEntity user;
}

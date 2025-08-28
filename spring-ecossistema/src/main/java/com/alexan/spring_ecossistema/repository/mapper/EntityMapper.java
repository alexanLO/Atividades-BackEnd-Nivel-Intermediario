package com.alexan.spring_ecossistema.repository.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import com.alexan.spring_ecossistema.model.Profile;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.repository.entity.ProfileEntity;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", expression = "java(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(model.getPassword(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()))")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createAt", source = "createAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    UserEntity toEntitySave(User model);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "avatarUrl", source = "avatarUrl")
    @Mapping(target = "birthDate", source = "birthDate")
    ProfileEntity toProfileEntitySave(Profile model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", expression = "java(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(model.getPassword(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()))")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createAt", source = "createAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    void updateEntityUser(User model, @MappingTarget UserEntity entity);

    List<User> toFullModelList(List<UserEntity> all);

    @Mapping(target = "loginAttempts", ignore = true)
    User toModel(UserEntity entity);

    List<User> toModelList(Page<UserEntity> all);
}

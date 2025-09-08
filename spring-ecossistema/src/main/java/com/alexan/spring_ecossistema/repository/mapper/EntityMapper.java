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

import com.alexan.spring_ecossistema.model.Profile;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.repository.entity.ProfileEntity;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.projections.UserSummaryProjection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", expression = "java(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(model.getPassword(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()))")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createAt", source = "createAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    UserEntity toEntitySave(User model);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "avatarUrl", source = "avatarUrl")
    @Mapping(target = "birthDate", source = "birthDate")
    ProfileEntity toProfileEntitySave(Profile model);

    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createAt", source = "createAt", defaultExpression = "java(java.time.LocalDateTime.now())")
    void updateEntityUser(User model, @MappingTarget UserEntity entity);

    User toModel(UserEntity entity);

    List<User> toListModel(List<UserEntity> entity);

    List<User> toFullModelList(List<UserEntity> entity);

    User toModel(UserSummaryProjection summaryProjection);

    List<User> toModelListPage(List<UserSummaryProjection> allCustom);

    // Adicione um método default para tratar a senha:
    default void updateEntityUserWithPassword(User model, @MappingTarget UserEntity entity) {
        updateEntityUser(model, entity);
        if (model.getPassword() != null) {
            entity.setPassword(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(
                    model.getPassword(), org.springframework.security.crypto.bcrypt.BCrypt.gensalt()));
        }
    }
}

package com.alexan.spring_ecossistema.controller.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.alexan.spring_ecossistema.controller.dto.requests.AlterarUserRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.UserFullInfoResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserResponse;
import com.alexan.spring_ecossistema.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    User toUserModel(UserRequest request);

    List<UserResponse> ToListResponse(List<User> listing);

    UserResponse ToResponse(User searchingById);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "loginAttempts", ignore = true)
    User toUserModel(AlterarUserRequest request);

    List<UserFullInfoResponse> ToListFullResponse(List<User> searchingAllWithProfileAndAttempts);
}
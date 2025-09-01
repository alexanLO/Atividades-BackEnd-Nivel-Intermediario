package com.alexan.spring_ecossistema.controller.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.alexan.spring_ecossistema.controller.dto.requests.AlterarUserRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserFilterRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.UserFilterResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserProfilAttemptsResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserResponse;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.model.UserFilter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, builder = @Builder(disableBuilder = true), injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User toUserModel(UserRequest request);

    UserFilter toRequestFilterModel(UserFilterRequest request);

    List<UserFilterResponse> toListFilterResponse(List<User> rUsers);

    List<UserResponse> ToListResponse(List<User> rUsers);

    List<UserProfilAttemptsResponse> ToListFullResponse(List<User> rUsers);

    User toUserModel(AlterarUserRequest request);

    UserResponse ToResponse(User searchingById);

}
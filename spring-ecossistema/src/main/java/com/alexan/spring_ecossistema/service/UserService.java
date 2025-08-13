package com.alexan.spring_ecossistema.service;

import java.util.List;
import java.util.Optional;

import com.alexan.spring_ecossistema.controller.dto.requests.AlterarUserRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;

public interface UserService {

    public void register(UserRequest request);

    public List<UserRequest> listing();

    public UserRequest findById(Long id);

    public void updateUser(Long id, AlterarUserRequest request);

    public void updateStatus(Long id, String status);

    public void deleteUser(Long id);

    public UserRequest findByLogin(String string);

    public Optional<UserRequest> findByEmail(String username);

}

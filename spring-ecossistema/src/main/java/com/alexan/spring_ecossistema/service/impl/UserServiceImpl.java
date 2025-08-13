package com.alexan.spring_ecossistema.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alexan.spring_ecossistema.controller.dto.requests.AlterarUserRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.model.enums.EnumStatus;
import com.alexan.spring_ecossistema.service.UserService;
import com.alexan.spring_ecossistema.validator.annotations.Auditable;

@Service
public class UserServiceImpl implements UserService {

    List<UserRequest> users = new ArrayList<>();
    private Long idCount = 1L;

    @Auditable(action = "Registering a new user")
    public void register(UserRequest request) {
        request.setId(idCount++);
        request.setCreationDate(LocalDateTime.now());
        users.add(request);
    }

    public List<UserRequest> listing() {
        return users.stream()
                .sorted(Comparator.comparing(UserRequest::getId))
                .collect(Collectors.toList());
    }

    public UserRequest findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    public void updateUser(Long id, AlterarUserRequest request) {
        UserRequest user = findById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
    }

    public void updateStatus(Long id, String status) {
        UserRequest user = findById(id);
        user.setStatus(EnumStatus.fromString(status));
    }

    public void deleteUser(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }

    public UserRequest findByLogin(String string) {
        return users.stream()
                .filter(u -> u.getEmail().equals(string))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @Override
    public Optional<UserRequest> findByEmail(String username) {
        return users.stream()
                .filter(u -> u.getEmail().equals(username))
                .findFirst()
                .map(user -> new UserRequest(user.getEmail(), user.getPassword(), user.getRole()));
    }
}

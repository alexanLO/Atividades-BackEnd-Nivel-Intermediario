package com.alexan.spring_ecossistema.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.requests.AlterarUserRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.UserFullInfoResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserResponse;
import com.alexan.spring_ecossistema.controller.mapper.UserMapper;
import com.alexan.spring_ecossistema.service.UserService;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UUID> register(@RequestBody @Valid UserRequest request) {
        log.info("Registrando um novo usuário com dados: {}", request);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(mapper.toUserModel(request)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listingAll(Pageable pageable) {
        log.info("Listando todos os usuários");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.ToListResponse(service.listingAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchingById(@PathVariable @NonNull UUID id) {
        log.info("Buscando usuário pelo id: {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(mapper.ToResponse(service.searchingById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable @NonNull UUID id,
            @RequestBody @Valid AlterarUserRequest request) {
        log.info("Atualizando o usuario com id: {}", id);

        service.updateUser(id, mapper.toUserModel(request));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NonNull UUID id) {
        log.info("Deletando o usuário com id: {}", id);

        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allWithProfileAndAttempts")
    public ResponseEntity<List<UserFullInfoResponse>> searchingAllWithProfileAndAttempts(Pageable pageable) {
        log.info("Buscando todos os usuários com perfil e tentativas de login");

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.ToListFullResponse(service.searchingAllWithProfileAndAttempts(pageable)));
    }
}

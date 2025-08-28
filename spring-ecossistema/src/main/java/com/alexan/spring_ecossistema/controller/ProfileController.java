package com.alexan.spring_ecossistema.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.requests.ProfileRequest;
import com.alexan.spring_ecossistema.controller.mapper.ProfileMapper;
import com.alexan.spring_ecossistema.service.ProfileService;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/profiles")
public class ProfileController {

    private final ProfileService service;
    private final ProfileMapper mapper;

    @PostMapping("/{id}")
    public ResponseEntity<UUID> create(@PathVariable @NonNull UUID id, @RequestBody ProfileRequest request) {
        log.info("Criando perfil para o usuario de id: {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(id, mapper.toProfileModel(request)));
    }

}

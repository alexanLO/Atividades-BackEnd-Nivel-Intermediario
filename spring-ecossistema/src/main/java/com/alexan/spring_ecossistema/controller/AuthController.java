package com.alexan.spring_ecossistema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;
import com.alexan.spring_ecossistema.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> log(@RequestBody @Valid LoginRequest request) {
        log.info("Usuario tentando fazer login com email: {}", request.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(service.login(request));
    }

}

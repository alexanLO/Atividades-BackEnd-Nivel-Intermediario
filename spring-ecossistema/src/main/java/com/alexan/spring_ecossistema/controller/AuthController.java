package com.alexan.spring_ecossistema.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;
import com.alexan.spring_ecossistema.interceptor.RequestInterceptor;
import com.alexan.spring_ecossistema.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> log(@RequestBody @Valid LoginRequest request) {
        log.info("Usuario tentando fazer login com email: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.login(request));
    }

}

package com.alexan.spring_ecossistema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;
import com.alexan.spring_ecossistema.infra.security.jwt.JwtService;
import com.alexan.spring_ecossistema.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        // Se a autenticação for bem-sucedida, você pode gerar um token JWT
        if (auth.isAuthenticated()) {
            var token = jwtService.generateToken((UserRequest) auth.getPrincipal());
            return new LoginResponse(token);
        } else {
            // Retorna uma resposta de erro se a autenticação falhar
            throw new RuntimeException("Falha na autenticação: credenciais inválidas");
        }
    }
}

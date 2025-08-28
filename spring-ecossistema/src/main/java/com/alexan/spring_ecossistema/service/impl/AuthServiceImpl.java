package com.alexan.spring_ecossistema.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;
import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.infra.security.SecurityUserDetails;
import com.alexan.spring_ecossistema.infra.security.jwt.JwtService;
import com.alexan.spring_ecossistema.model.enums.EnumStatus;
import com.alexan.spring_ecossistema.repository.LoginAttemptRepository;
import com.alexan.spring_ecossistema.repository.UserRepository;
import com.alexan.spring_ecossistema.repository.entity.LoginAttemptEntity;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String INVALID_CREDENTIAL = "Credenciais invalidas";
    private static final String HALF_ATTEMPTS = "Voce so tem mais 2 tentativas caso falhe sua conta sera bloqueada";
    private static final String USER_BLOCKED = "Usuario bloqueado por atingir o maximo de tentativas";

    private static final String SUCCESS = "0";
    private static final String FAILURE = "1";

    private static final int halfAttempts = 3;
    private static final int maximumAttempts = 5;


    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final LoginAttemptRepository attemptRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        boolean authenticated = false;
        var user = userRepository.findByEmailWithAttempts(request.getEmail()).orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
        
        verifyLogins(user);
        
        try {
            var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            var auth = authenticationManager.authenticate(authToken);
            authenticated = auth.isAuthenticated();

            if (authenticated) {
                var token = jwtService.generateToken((SecurityUserDetails) auth.getPrincipal());
                return new LoginResponse(token);
            }

        } catch (AuthenticationException ex) {

            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), INVALID_CREDENTIAL);

        } finally {
            registerLoginAttempts(user, authenticated);
        }

        return null;
    }

    @Transactional
    private void verifyLogins(UserEntity user) {

        if (user.getLoginAttempts().size() == halfAttempts && user.getLoginAttempts().stream()
                .sorted(Comparator.comparing(LoginAttemptEntity::getAttemptTime).reversed())
                .limit(halfAttempts)
                .allMatch(attempt -> attempt.getSuccess().equals(FAILURE))) {
               throw new BusinessException("Error: ", HALF_ATTEMPTS);
        }

        if (user.getLoginAttempts().size() >= maximumAttempts && user.getLoginAttempts().stream()
                .sorted(Comparator.comparing(LoginAttemptEntity::getAttemptTime).reversed())
                .limit(maximumAttempts)
                .allMatch(attempt -> attempt.getSuccess().equals(FAILURE))) {

            user.setStatus(EnumStatus.BLOQUEADO);
            userRepository.save(user);

            throw new BusinessException(HttpStatus.LOCKED.value(), USER_BLOCKED);
        }
    }

    private void registerLoginAttempts(UserEntity user, boolean authenticated) {
        LoginAttemptEntity attempt = LoginAttemptEntity.builder()
                .attemptTime(LocalDateTime.now())
                .user(user)
                .success(authenticated ? SUCCESS : FAILURE)
                .build();

        attemptRepository.save(attempt);
    }
}

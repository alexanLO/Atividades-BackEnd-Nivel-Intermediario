package com.alexan.spring_ecossistema.infra.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.infra.security.SecurityUserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(SecurityUserDetails user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            return JWT.create()
                    .withIssuer(jwtProperties.getIssuer())
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationTime())
                    .withClaim("roles",
                            user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o token JWT: " + e.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            return JWT.require(algorithm)
                    .withIssuer(jwtProperties.getIssuer())
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException(new Error("Token inválido ou expirado: " + e.getMessage()));
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}

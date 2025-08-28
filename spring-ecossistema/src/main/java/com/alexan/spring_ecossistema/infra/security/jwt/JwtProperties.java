package com.alexan.spring_ecossistema.infra.security.jwt;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt.security.token")
public class JwtProperties {

    private String secret;
    private long expiration;
    private String header;
    private String prefix;
    private String issuer;
    private List<String> roles;
}

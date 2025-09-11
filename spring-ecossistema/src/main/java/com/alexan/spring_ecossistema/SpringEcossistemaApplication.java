package com.alexan.spring_ecossistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(info = @Info(title = "Gerenciamento de Usuário da Empresarial", version = "1.2.15", description = "Esta API é responsavel no gerenciamento de usúarios dos produtos da empresa", license = @License(name = "Alexan License", url = "https://www.linkedin.com/in/alexan-limaa/")), security = {
		@SecurityRequirement(name = "bearerAuth")
})
@SecuritySchemes(value = {
		@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT") })
@SpringBootApplication
public class SpringEcossistemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcossistemaApplication.class, args);
	}
}
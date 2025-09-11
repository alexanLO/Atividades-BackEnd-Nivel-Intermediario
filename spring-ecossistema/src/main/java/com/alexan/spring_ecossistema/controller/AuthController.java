package com.alexan.spring_ecossistema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;
import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Autenticação.", description = "Gerenciamento de logins para permissões")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Solicitação Inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "403", description = "Proibido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "422", description = "Não é possivel processar os dados recebidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "429", description = "Muitas solicitações realizadas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
        @ApiResponse(responseCode = "503", description = "Serviço Indiponivel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    @Operation(summary = "Registra um novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autenticado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> log(@RequestBody @Valid LoginRequest request) {
        log.info("Usuario tentando fazer login com email: {}", request.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(service.login(request));
    }

}

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
import com.alexan.spring_ecossistema.controller.dto.requests.UserFilterRequest;
import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.UserFilterResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserProfilAttemptsResponse;
import com.alexan.spring_ecossistema.controller.dto.responses.UserResponse;
import com.alexan.spring_ecossistema.controller.mapper.UserMapper;
import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.service.UserService;

import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Usuários", description = "Gerenciamento de Usuários")
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
@RequestMapping("/v1/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Operation(summary = "Registra um novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registra usuário com sucesso.", content = @Content(schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "409", description = "Usuário já existe usuário com esse nome ou email.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    })
    @PostMapping()
    public ResponseEntity<UUID> register(@RequestBody @Valid UserRequest request) {
        log.info("Registrando um novo usuario com os dados: {}", request);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(mapper.toUserModel(request)));
    }

    @Operation(summary = "Lista todos os usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registra usuário com sucesso.", content = @Content(schema = @Schema(implementation = UserResponse.class))),
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> listingAll(Pageable pageable) {
        log.info("Listando todos os usuarios");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.ToListResponse(service.listingAll(pageable)));
    }

    @Operation(summary = "Busca um usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registra usuário com sucesso.", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchingById(@PathVariable("id") @NonNull UUID id) {
        log.info("Buscando usuario pelo id: {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(mapper.ToResponse(service.searchingById(id)));
    }

    @Operation(summary = "Alterar dados do usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados alterados com sucesso", content = @Content(schema = @Schema(implementation = AlterarUserRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable @NonNull UUID id,
            @RequestBody @Valid AlterarUserRequest request) {
        log.info("Atualizando o usuario com id: {}", id);

        service.updateUser(id, mapper.toUserModel(request));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Busca um usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NonNull UUID id) {
        log.info("Deletando o usuario com id: {}", id);

        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca um usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando usuários com perfil e hitoricos de logins com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfilAttemptsResponse.class))),
    })
    @GetMapping("/allWithProfileAndAttempts")
    public ResponseEntity<List<UserProfilAttemptsResponse>> searchingAllWithProfileAndAttempts(Pageable pageable) {
        log.info("Listando todos os usuarios com perfil e tentativas de login");

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.ToListFullResponse(service.searchingAllWithProfileAndAttempts(pageable)));
    }

    @Operation(summary = "Busca um usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando os cincos novos usuários", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    })
    @GetMapping("/new")
    public ResponseEntity<List<UserResponse>> listingAllNewUsers() {
        log.info("Listando todos os novos usuários");
        // * Caso precise futuramente implementar pageble para paginação */
        return ResponseEntity.ok().body(mapper.ToListResponse(service.listingAllNewUsers()));
    }

    @Operation(summary = "Busca um usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando usuários por filtro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserFilterResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class)))
    })
    @PostMapping("/filter")
    public ResponseEntity<List<UserFilterResponse>> listingByFilter(@RequestBody UserFilterRequest request) {
        return ResponseEntity.ok()
                .body(mapper.toListFilterResponse(service.listintByFilter(mapper.toRequestFilterModel(request))));
    }

}

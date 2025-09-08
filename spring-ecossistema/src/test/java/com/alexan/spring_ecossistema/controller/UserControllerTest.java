package com.alexan.spring_ecossistema.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alexan.spring_ecossistema.controller.dto.requests.UserRequest;
import com.alexan.spring_ecossistema.controller.mapper.UserMapper;
import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.BusinessExceptionHandler;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.mocks.UserMock;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as funcionalidades da camada controller")
public class UserControllerTest extends UserMock {

    private static final String NOT_FOUND = "Usuario nao encontrado.";
    private static final String USER_EXIST = "Já existe um usuário com esse nome ou email";

    private static final String URL_BASE = "/v1/users";
    private static final String URL_BY_ID = URL_BASE + "/{id}";

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService service;

    @Mock
    private UserMapper mapper;

    private UserController controller;

    @BeforeEach
    void setup() throws Exception {

        service = mock(UserService.class);
        mapper = mock(UserMapper.class);

        controller = new UserController(service, mapper);

        objectMapper.findAndRegisterModules();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(BusinessExceptionHandler.class)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    @Test
    @DisplayName("POST - cria usuário → retorna 201.")
    void mustCreateUserSuccess() throws Exception {

        var request = createUserFaker();

        when(mapper.toUserModel(any(UserRequest.class))).thenReturn(request);
        when(service.register(any(User.class))).thenReturn(ID);

        mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserRequestFaker())))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("\"" + ID.toString() + "\""));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(service, times(1)).register(captor.capture());
        assertEquals(request, captor.getValue());
    }

    @Test
    @DisplayName("POST - com e-mail invalido → retorna 409.")
    void mustThrowInvalidEmailBusinessError() throws Exception {

        when(mapper.toUserModel(any(UserRequest.class))).thenReturn(createUserFaker());
        when(service.register(any(User.class)))
                .thenThrow(new BusinessException(HttpStatus.CONFLICT.value(), USER_EXIST));

        mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserRequestFaker())))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals(USER_EXIST, result.getResolvedException().getMessage()));

    }

    @Test
    @DisplayName("GET - retorna usuário correto → retorna 200")
    void mustSearchUserSuccess() throws Exception {

        when(mapper.ToResponse(any(User.class))).thenReturn(createUserResponseFaker());
        when(service.searchingById(any(UUID.class))).thenReturn(createUserFaker());

        mockMvc.perform(get(URL_BY_ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists());

    }

    @Test
    @DisplayName("GET - usuario não encontrado → retorna 404.")
    void mustThrowUserErrorNotExist() throws Exception {

        when(service.searchingById(any(UUID.class))).thenThrow(new NotFoundException(NOT_FOUND));

        mockMvc.perform(get(URL_BY_ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals(NOT_FOUND, result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("DELETE - remove usuário → retorna 204.")
    void mustDeleteSuccess() throws Exception {

        doNothing().when(service).deleteUser(ID);

        mockMvc.perform(delete(URL_BY_ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}

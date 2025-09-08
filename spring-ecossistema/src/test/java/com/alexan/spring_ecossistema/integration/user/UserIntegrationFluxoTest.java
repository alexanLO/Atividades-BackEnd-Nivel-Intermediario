package com.alexan.spring_ecossistema.integration.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alexan.spring_ecossistema.mocks.UserMock;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Fluxo de Integração do Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationFluxoTest extends UserMock {

    private static final String URL_BASE = "/v1/users";
    private static final String URL_BY_ID = URL_BASE + "/{id}";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private UUID userId;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Criar usuário.")
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void mustCreate() throws Exception {

        MvcResult result = mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserRequestFaker())))
                .andExpect(status().isCreated())
                .andReturn();

        String idString = result.getResponse().getContentAsString().replace("\"", "");
        userId = UUID.fromString(idString);
    }

    @Test
    @Order(2)
    @DisplayName("Buscar usuário.")
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void mustSearch() throws Exception {

        mockMvc.perform(get(URL_BY_ID, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Alexan Oliveira"))
                .andExpect(jsonPath("$.email").value("alexan@Empresa.com"));
    }

    @Test
    @Order(3)
    @DisplayName("Atualizar usuário.")
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void mustUpdate() throws Exception {

        String updateUserJson = """
                    {
                        "fullName": "Alexan Lima de Oliveira",
                        "email": "alexan.lima@Empresa.com"
                    }
                """;

        mockMvc.perform(put(URL_BY_ID, userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateUserJson))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("Fazer uma nova buscar do usuário atualizado.")
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void mustSearchUserUpdate() throws Exception {

        mockMvc.perform(get(URL_BY_ID, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("fullName").value("Alexan Lima de Oliveira"))
                .andExpect(jsonPath("email").value("alexan.lima@Empresa.com"));
    }

    @Test
    @Order(5)
    @DisplayName("Confirmar que o usuário foi deletado.")
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void mustDelete() throws Exception {

        mockMvc.perform(delete(URL_BY_ID, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(URL_BY_ID, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

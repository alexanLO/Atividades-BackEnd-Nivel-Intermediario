package com.alexan.spring_ecossistema.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.mocks.UserMock;
import com.alexan.spring_ecossistema.repository.UserRepository;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.mapper.EntityMapper;

@ExtendWith(MockitoExtension.class)
@DisplayName("Validar as funcionalidades da camada service")
public class UserServiceImplTest extends UserMock {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityMapper mapper;

    @InjectMocks
    private UserServiceImpl serviceImpl;

    @Test
    @DisplayName("Usar ArgumentCaptor para validar se o User salvo tem os dados corretos.")
    void mustValidateUserSavedCorrectData() {

        var entity = createUserEntityFaker();
        var request = createUserFaker();

        when(userRepository.findByFullNameOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
        when(mapper.toEntitySave(request)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(entity);

        serviceImpl.register(request);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository, times(1)).save(captor.capture());
        assertEquals(entity, captor.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = { "dev@Empresa.com", "gerente@Empresa.com", "as@Empresa.com", "an@Empresa.com" })
    @DisplayName("Criar usuário já existente, validar múltiplos e-mails inválidos.")
    void mustValidateMultipleInvalidEmails(String invalidEmail) {

        var entity = createUserEntityFaker();
        var request = createUserFaker();
        request.setEmail(invalidEmail);

        when(userRepository.findByFullNameOrEmail(anyString(), anyString())).thenReturn(Optional.of(entity));

        var exResult = assertThrows(BusinessException.class, () -> serviceImpl.register(request));
        
        assertEquals(HttpStatus.CONFLICT.value(), Integer.valueOf(exResult.getErroCode()));
        assertTrue(exResult.getMessage().contains(USER_EXIST));
    }

    @Test
    @DisplayName("Buscar usuário por ID válido.")
    void mustsearchingByIdSuccess() {
        var expected = createUserFaker();
        var userSummary = createUserSummaryFaker();

        when(userRepository.findProjectedById(any(UUID.class))).thenReturn(Optional.of(userSummary));
        when(mapper.toModel(userSummary)).thenReturn(expected);

        var result = serviceImpl.searchingById(ID);

        assertEquals(expected, result);
        verify(userRepository, times(1)).findProjectedById(ID);
        verify(mapper, times(1)).toModel(userSummary);
    }

    @Test
    @DisplayName("Buscar usuário por ID inexistente.")
    void mustThrowUserNotFoundException() {
        when(userRepository.findProjectedById(any(UUID.class))).thenThrow(new NotFoundException(NOT_FOUND));

        var exResult = assertThrows(NotFoundException.class, () -> serviceImpl.searchingById(ID));

        assertTrue(exResult.getMessage().contains(NOT_FOUND));
    }

    @Test
    @DisplayName("Atualizar dados do usuário.")
    void mustUpdateSuccess() {
        var entity = createUserEntityFaker();
        var model = createUserFaker();

        when(userRepository.findById(model.getId())).thenReturn(Optional.of(entity));
        doNothing().when(mapper).updateEntityUser(model, entity);
        when(userRepository.save(entity)).thenReturn(entity);

        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getFullName(), model.getFullName());
        assertEquals(entity.getPassword(), model.getPassword());
        assertEquals(entity.getEmail(), model.getEmail());

        serviceImpl.updateUser(ID, model);

        verify(userRepository, times(1)).findById(model.getId());
        verify(mapper, times(1)).updateEntityUser(model, entity);
        verify(userRepository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Deletar usuário")
    void mustDeleteSuccess() {

        var entity = createUserEntityFaker();

        when(userRepository.findById(ID)).thenReturn(Optional.of(entity));
        doNothing().when(userRepository).delete(entity);

        serviceImpl.deleteUser(ID);

        verify(userRepository, times(1)).findById(ID);
        verify(userRepository, times(1)).delete(entity);
    }
}

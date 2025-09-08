package com.alexan.spring_ecossistema.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.model.UserFilter;
import com.alexan.spring_ecossistema.repository.UserRepository;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.especification.UserEspecification;
import com.alexan.spring_ecossistema.repository.mapper.EntityMapper;
import com.alexan.spring_ecossistema.service.UserService;
import com.alexan.spring_ecossistema.validator.annotations.Auditable;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_EXIST = "Já existe um usuário com esse nome ou email";
    private static final String NOT_FOUND = "Usuario nao encontrado.";

    private final UserRepository repository;

    private final EntityMapper mapper;

    @Transactional
    @Auditable(action = "Registrando um novo usuario")
    public UUID register(User request) {

        if (repository.findByFullNameOrEmail(request.getFullName(), request.getEmail()).isPresent()) {
              throw new BusinessException(HttpStatus.CONFLICT.value(), USER_EXIST);
        }

        return repository.save(mapper.toEntitySave(request)).getId();
    }

    @Override
    @Auditable(action = "Listando todos os usuarios")
    public List<User> listingAll(Pageable pageable) {
        return mapper.toModelListPage(repository.findProjectedAllBy(pageable));
    }

    @Override
    @Auditable(action = "Buscando usuario por ID")
    public User searchingById(UUID id) {
        return mapper
                .toModel(repository.findProjectedById(id)
                        .orElseThrow(() -> new NotFoundException(NOT_FOUND)));
    }

    @Override
    @Transactional
    @Auditable(action = "Atualizando dados do usuario")
    public void updateUser(UUID id, User request) {
        repository.findById(id).map(entity -> {
            mapper.updateEntityUser(request, entity);
            return repository.save(entity);
        }).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    @Override
    @Transactional
    @Auditable(action = "Deletando usuario por id")
    public void deleteUser(UUID id) {
        repository.findById(id).map(entity -> {
            repository.delete(entity);
            return Void.TYPE;
        }).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    @Override
    @Auditable(action = "Buscando usuario por email")
    public User findByEmail(String username) {

        return mapper.toModel(repository.findByEmailWithAttempts(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND)));
    }

    @Override
    @Auditable(action = "Listando usuarios com perfis e tentativas de logins")
    public List<User> searchingAllWithProfileAndAttempts(Pageable pageable) {
        Page<UserEntity> page = repository.findAll(pageable);

        return mapper
                .toFullModelList(repository.findAllWithProfileAndAttempts(page.stream().collect(Collectors.toList())));
    }

    @Override
    @Auditable(action = "Listando novos usuarios")
    public List<User> listingAllNewUsers() {
        return mapper.toListModel(repository.findAllNewUser());
    }

    public List<User> listingLatestRegistered() {
        // * Criado apenas para atividade ainda não tem uso especifico */
        return mapper.toListModel(repository.findLatestRegistered(PageRequest.of(0, 5)));
    }

    @Override
    @Auditable(action = "Listando usuários por filtros")
    public List<User> listintByFilter(UserFilter request) {
        return mapper.toListModel(repository.findAll(UserEspecification.byFilter(request)));
    };
}

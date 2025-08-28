package com.alexan.spring_ecossistema.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.model.User;
import com.alexan.spring_ecossistema.repository.UserRepository;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.mapper.EntityMapper;
import com.alexan.spring_ecossistema.service.UserService;
import com.alexan.spring_ecossistema.validator.annotations.Auditable;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final EntityMapper mapper;

    @Transactional
    @Auditable(action = "Registrando um novo usuário")
    public UUID register(User request) {

        UserEntity user = repository.findByFullName(request.getFullName());

        if (user != null) {
            throw new RuntimeException("Usuario já existe com id: " + user.getId());
        }

        return repository.save(mapper.toEntitySave(request)).getId();
    }

    @Override
    @Auditable(action = "Listando todos os usuários")
    public List<User> listingAll(Pageable pageable) {
        return mapper.toModelList(repository.findAll(pageable));
    }

    @Override
    @Auditable(action = "Buscando usuário por ID")
    public User searchingById(UUID id) {
        return mapper
                .toModel(repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario nao encontrado.")));
    }

    @Override
    @Transactional
    @Auditable(action = "Atualizando usuário")
    public void updateUser(UUID id, User request) {
        repository.findById(id).map(entity -> {
            mapper.updateEntityUser(request, entity);
            return repository.save(entity);
        }).orElseThrow(() -> new NotFoundException("Usuario nao encontrado."));

    }

    @Override
    @Transactional
    @Auditable(action = "Deletando usuário")
    public void deleteUser(UUID id) {
        repository.findById(id).map(entity -> {
            repository.delete(entity);
            return Void.TYPE;
        }).orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
    }

    @Override
    @Auditable(action = "Buscando usuário por email")
    public User findByEmail(String username) {

        return mapper.toModel(repository.findByEmailWithAttempts(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado.")));
    }

    @Override
    public List<User> searchingAllWithProfileAndAttempts(Pageable pageable) {
        Page<UserEntity> page = repository.findAll(pageable);

        return mapper
                .toFullModelList(repository.findAllWithProfileAndAttempts(page.stream().collect(Collectors.toList())));
    }

}

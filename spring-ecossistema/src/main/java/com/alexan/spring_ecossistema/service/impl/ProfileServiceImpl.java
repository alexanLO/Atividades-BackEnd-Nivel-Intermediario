package com.alexan.spring_ecossistema.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.exceptions.BusinessException;
import com.alexan.spring_ecossistema.exceptions.NotFoundException;
import com.alexan.spring_ecossistema.model.Profile;
import com.alexan.spring_ecossistema.repository.ProfileRepository;
import com.alexan.spring_ecossistema.repository.UserRepository;
import com.alexan.spring_ecossistema.repository.mapper.EntityMapper;
import com.alexan.spring_ecossistema.service.ProfileService;
import com.alexan.spring_ecossistema.validator.annotations.Auditable;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final EntityMapper mapper;

    @Override
    @Transactional
    @Auditable(action = "Criar perfil de usuario")
    public UUID create(UUID id, Profile profileModel) {

        userRepository.findById(id).map(u -> {

            if (u.getProfile() != null) {
                throw new BusinessException(HttpStatus.CONFLICT.value(), "Usuário já possui um perfil.");
            }

            var profileEntity = mapper.toProfileEntitySave(profileModel);
            profileEntity.setUser(u);
            u.setProfile(profileEntity);
            userRepository.save(u);
            return profileRepository.save(profileEntity).getId();
        }).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        return null;
    }
}

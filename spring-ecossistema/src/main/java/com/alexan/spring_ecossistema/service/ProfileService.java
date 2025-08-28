package com.alexan.spring_ecossistema.service;

import java.util.UUID;

import com.alexan.spring_ecossistema.model.Profile;

public interface ProfileService {

    public UUID create(UUID id, Profile profileModel);
}

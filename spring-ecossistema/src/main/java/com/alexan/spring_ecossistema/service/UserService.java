package com.alexan.spring_ecossistema.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.alexan.spring_ecossistema.model.User;

public interface UserService {

    public UUID register(User request);

    public User findByEmail(String username);

    public List<User> listingAll(Pageable pageable);

    public User searchingById(UUID id);

    public void updateUser(UUID id, User request);

    public void deleteUser(UUID id);

    public List<User> searchingAllWithProfileAndAttempts(Pageable pageable);

}

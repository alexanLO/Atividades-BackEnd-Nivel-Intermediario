package com.alexan.spring_ecossistema.service;

import java.util.List;

import com.alexan.spring_ecossistema.controller.dto.AlterarUser;
import com.alexan.spring_ecossistema.controller.dto.User;

public interface UserService {

    public void register(User request);

    public List<User> listing();

    public User findById(Long id);

    public void updateUser(Long id, AlterarUser request);

    public void updateStatus(Long id, String status);

    public void deleteUser(Long id);
}

package com.alexan.spring_ecossistema.service;

import com.alexan.spring_ecossistema.controller.dto.requests.LoginRequest;
import com.alexan.spring_ecossistema.controller.dto.responses.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}

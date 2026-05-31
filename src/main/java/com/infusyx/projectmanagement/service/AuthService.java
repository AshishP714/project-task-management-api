package com.infusyx.projectmanagement.service;

import com.infusyx.projectmanagement.dto.request.LoginRequest;
import com.infusyx.projectmanagement.dto.request.RegisterRequest;
import com.infusyx.projectmanagement.dto.response.AuthResponse;

public interface AuthService {

	AuthResponse register(RegisterRequest request);

	AuthResponse login(LoginRequest request);
}
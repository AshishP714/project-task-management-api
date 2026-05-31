package com.infusyx.projectmanagement.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infusyx.projectmanagement.dto.request.LoginRequest;
import com.infusyx.projectmanagement.dto.request.RegisterRequest;
import com.infusyx.projectmanagement.dto.response.AuthResponse;
import com.infusyx.projectmanagement.entity.User;
import com.infusyx.projectmanagement.repository.UserRepository;
import com.infusyx.projectmanagement.security.JwtUtil;
import com.infusyx.projectmanagement.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	@Override
	public AuthResponse register(RegisterRequest request) {

		User user = User.builder().name(request.getName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();

		User savedUser = userRepository.save(user);

		String token = jwtUtil.generateToken(savedUser.getEmail());

		return AuthResponse.builder().token(token).email(savedUser.getEmail()).role(savedUser.getRole()).build();
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

		String token = jwtUtil.generateToken(user.getEmail());

		return AuthResponse.builder().token(token).email(user.getEmail()).role(user.getRole()).build();
	}
}
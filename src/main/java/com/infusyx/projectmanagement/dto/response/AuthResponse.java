package com.infusyx.projectmanagement.dto.response;

import com.infusyx.projectmanagement.entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String token;

    private String email;

    private Role role;
}
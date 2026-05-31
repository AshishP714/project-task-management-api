package com.infusyx.projectmanagement.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamMemberResponse {

    private Long id;

    private String name;

    private String email;

    private String role;

    private String department;
    
}
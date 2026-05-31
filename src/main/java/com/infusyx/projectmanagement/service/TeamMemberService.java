package com.infusyx.projectmanagement.service;

import java.util.List;

import com.infusyx.projectmanagement.dto.request.TeamMemberRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TeamMemberResponse;

public interface TeamMemberService {

    TeamMemberResponse addTeamMember(TeamMemberRequest request);

    TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest request);

    ApiResponse deleteTeamMember(Long id);

    List<TeamMemberResponse> getAllTeamMembers();

    TeamMemberResponse getTeamMemberById(Long id);
}
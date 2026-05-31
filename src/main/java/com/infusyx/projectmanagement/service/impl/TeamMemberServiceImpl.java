package com.infusyx.projectmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infusyx.projectmanagement.dto.request.TeamMemberRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TeamMemberResponse;
import com.infusyx.projectmanagement.entity.TeamMember;
import com.infusyx.projectmanagement.exception.ResourceNotFoundException;
import com.infusyx.projectmanagement.repository.TeamMemberRepository;
import com.infusyx.projectmanagement.service.TeamMemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

	private final TeamMemberRepository teamMemberRepository;

	@Override
	public TeamMemberResponse addTeamMember(TeamMemberRequest request) {

		TeamMember member = TeamMember.builder().name(request.getName()).email(request.getEmail())
				.role(request.getRole()).department(request.getDepartment()).build();

		TeamMember savedMember = teamMemberRepository.save(member);

		return mapToResponse(savedMember);
	}

	@Override
	public TeamMemberResponse updateTeamMember(Long id, TeamMemberRequest request) {

		TeamMember member = teamMemberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

		member.setName(request.getName());
		member.setEmail(request.getEmail());
		member.setRole(request.getRole());
		member.setDepartment(request.getDepartment());

		TeamMember updatedMember = teamMemberRepository.save(member);

		return mapToResponse(updatedMember);
	}

	@Override
	public ApiResponse deleteTeamMember(Long id) {

		TeamMember member = teamMemberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

		teamMemberRepository.delete(member);

		return new ApiResponse("Team member deleted successfully", true);
	}

	@Override
	public List<TeamMemberResponse> getAllTeamMembers() {

		return teamMemberRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public TeamMemberResponse getTeamMemberById(Long id) {

		TeamMember member = teamMemberRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

		return mapToResponse(member);
	}

	private TeamMemberResponse mapToResponse(TeamMember member) {

		return TeamMemberResponse.builder().id(member.getId()).name(member.getName()).email(member.getEmail())
				.role(member.getRole()).department(member.getDepartment()).build();
	}
}
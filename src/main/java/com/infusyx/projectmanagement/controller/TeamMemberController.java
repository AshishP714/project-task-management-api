package com.infusyx.projectmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.infusyx.projectmanagement.dto.request.TeamMemberRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TeamMemberResponse;
import com.infusyx.projectmanagement.service.TeamMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class TeamMemberController {

	private final TeamMemberService teamMemberService;

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','MEMBER')")
	public ResponseEntity<TeamMemberResponse> addTeamMember(@Valid @RequestBody TeamMemberRequest request) {

		return new ResponseEntity<>(teamMemberService.addTeamMember(request), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TeamMemberResponse> updateTeamMember(@PathVariable Long id,
			@Valid @RequestBody TeamMemberRequest request) {

		return ResponseEntity.ok(teamMemberService.updateTeamMember(id, request));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteTeamMember(@PathVariable Long id) {

		return ResponseEntity.ok(teamMemberService.deleteTeamMember(id));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<List<TeamMemberResponse>> getAllTeamMembers() {

		return ResponseEntity.ok(teamMemberService.getAllTeamMembers());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TeamMemberResponse> getTeamMemberById(@PathVariable Long id) {

		return ResponseEntity.ok(teamMemberService.getTeamMemberById(id));
	}
}
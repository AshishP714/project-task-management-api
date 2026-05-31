package com.infusyx.projectmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infusyx.projectmanagement.dto.request.ProjectRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.ProjectResponse;
import com.infusyx.projectmanagement.entity.Project;
import com.infusyx.projectmanagement.exception.ResourceNotFoundException;
import com.infusyx.projectmanagement.repository.ProjectRepository;
import com.infusyx.projectmanagement.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	@Override
	public ProjectResponse createProject(ProjectRequest request) {

		Project project = Project.builder().projectName(request.getProjectName()).description(request.getDescription())
				.startDate(request.getStartDate()).endDate(request.getEndDate()).status(request.getStatus()).build();

		Project savedProject = projectRepository.save(project);

		return mapToResponse(savedProject);
	}

	@Override
	public ProjectResponse updateProject(Long id, ProjectRequest request) {

		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

		project.setProjectName(request.getProjectName());
		project.setDescription(request.getDescription());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		project.setStatus(request.getStatus());

		Project updatedProject = projectRepository.save(project);

		return mapToResponse(updatedProject);
	}

	@Override
	public ApiResponse deleteProject(Long id) {

		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

		projectRepository.delete(project);

		return new ApiResponse("Project deleted successfully", true);
	}

	@Override
	public List<ProjectResponse> getAllProjects() {

		return projectRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public ProjectResponse getProjectById(Long id) {

		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

		return mapToResponse(project);
	}

	private ProjectResponse mapToResponse(Project project) {

		return ProjectResponse.builder().id(project.getId()).projectName(project.getProjectName())
				.description(project.getDescription()).startDate(project.getStartDate()).endDate(project.getEndDate())
				.status(project.getStatus()).build();
	}
}
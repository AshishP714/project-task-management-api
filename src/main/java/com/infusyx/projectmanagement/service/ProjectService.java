package com.infusyx.projectmanagement.service;

import java.util.List;

import com.infusyx.projectmanagement.dto.request.ProjectRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.ProjectResponse;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    ApiResponse deleteProject(Long id);

    List<ProjectResponse> getAllProjects();

    ProjectResponse getProjectById(Long id);
}
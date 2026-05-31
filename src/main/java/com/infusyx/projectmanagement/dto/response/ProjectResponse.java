package com.infusyx.projectmanagement.dto.response;

import java.time.LocalDate;

import com.infusyx.projectmanagement.entity.ProjectStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectResponse {

	private Long id;

    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private ProjectStatus status;
}
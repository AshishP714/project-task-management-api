package com.infusyx.projectmanagement.dto.request;

import java.time.LocalDate;

import com.infusyx.projectmanagement.entity.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

	@NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Project status is required")
    private ProjectStatus status;
    
}
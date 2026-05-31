package com.infusyx.projectmanagement.dto.response;

import java.time.LocalDate;

import com.infusyx.projectmanagement.entity.Priority;
import com.infusyx.projectmanagement.entity.TaskStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private TaskStatus status;

    private LocalDate dueDate;

    private Long projectId;

    private String projectName;

    private Long memberId;

    private String memberName;
}
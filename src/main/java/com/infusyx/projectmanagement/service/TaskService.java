package com.infusyx.projectmanagement.service;

import java.util.List;

import com.infusyx.projectmanagement.dto.request.TaskRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TaskResponse;

public interface TaskService {

	TaskResponse createTask(TaskRequest request);

	TaskResponse updateTask(Long id, TaskRequest request);

	ApiResponse deleteTask(Long id);

	List<TaskResponse> getAllTasks();

	TaskResponse getTaskById(Long id);
}
package com.infusyx.projectmanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infusyx.projectmanagement.dto.request.TaskRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TaskResponse;
import com.infusyx.projectmanagement.entity.Project;
import com.infusyx.projectmanagement.entity.Task;
import com.infusyx.projectmanagement.entity.TeamMember;
import com.infusyx.projectmanagement.exception.ResourceNotFoundException;
import com.infusyx.projectmanagement.repository.ProjectRepository;
import com.infusyx.projectmanagement.repository.TaskRepository;
import com.infusyx.projectmanagement.repository.TeamMemberRepository;
import com.infusyx.projectmanagement.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	private final TeamMemberRepository teamMemberRepository;

	@Override
	public TaskResponse createTask(TaskRequest request) {

		Project project = projectRepository.findById(request.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		TeamMember member = teamMemberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("Team member not found"));

		Task task = Task.builder().title(request.getTitle()).description(request.getDescription())
				.priority(request.getPriority()).status(request.getStatus()).dueDate(request.getDueDate())
				.project(project).teamMember(member).build();

		Task savedTask = taskRepository.save(task);

		return mapToResponse(savedTask);
	}

	@Override
	public TaskResponse updateTask(Long id, TaskRequest request) {

		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

		Project project = projectRepository.findById(request.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		TeamMember member = teamMemberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("Team member not found"));

		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setPriority(request.getPriority());
		task.setStatus(request.getStatus());
		task.setDueDate(request.getDueDate());
		task.setProject(project);
		task.setTeamMember(member);

		Task updatedTask = taskRepository.save(task);

		return mapToResponse(updatedTask);
	}

	@Override
	public ApiResponse deleteTask(Long id) {

		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

		taskRepository.delete(task);

		return new ApiResponse("Task deleted successfully", true);
	}

	@Override
	public List<TaskResponse> getAllTasks() {

		return taskRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public TaskResponse getTaskById(Long id) {

		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));

		return mapToResponse(task);
	}

	private TaskResponse mapToResponse(Task task) {

		return TaskResponse.builder().id(task.getId()).title(task.getTitle()).description(task.getDescription())
				.priority(task.getPriority()).status(task.getStatus()).dueDate(task.getDueDate())
				.projectId(task.getProject().getId()).projectName(task.getProject().getProjectName())
				.memberId(task.getTeamMember().getId()).memberName(task.getTeamMember().getName()).build();
	}
}
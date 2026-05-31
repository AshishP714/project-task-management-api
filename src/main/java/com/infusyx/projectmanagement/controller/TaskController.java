package com.infusyx.projectmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.infusyx.projectmanagement.dto.request.TaskRequest;
import com.infusyx.projectmanagement.dto.response.ApiResponse;
import com.infusyx.projectmanagement.dto.response.TaskResponse;
import com.infusyx.projectmanagement.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
		return new ResponseEntity<>(taskService.createTask(request), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
		return ResponseEntity.ok(taskService.updateTask(id, request));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.deleteTask(id));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','MEMBER')")
	public ResponseEntity<List<TaskResponse>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','MEMBER')")
	public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
}
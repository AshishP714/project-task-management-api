package com.infusyx.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infusyx.projectmanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
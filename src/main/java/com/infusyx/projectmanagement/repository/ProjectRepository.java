package com.infusyx.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infusyx.projectmanagement.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
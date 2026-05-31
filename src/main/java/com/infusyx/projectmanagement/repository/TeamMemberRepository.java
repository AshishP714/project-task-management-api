package com.infusyx.projectmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infusyx.projectmanagement.entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{

}
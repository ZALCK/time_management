package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Role;

public interface IRoleDAO extends JpaRepository<Role, Long> {
	
	public Role findByName(String name);
}

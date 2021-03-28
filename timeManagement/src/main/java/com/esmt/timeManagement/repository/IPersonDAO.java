package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Person;

public interface IPersonDAO extends JpaRepository<Person, Long> {
	
	public Person findByEmail(String email);
}

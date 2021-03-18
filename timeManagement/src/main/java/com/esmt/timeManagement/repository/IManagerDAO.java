package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Manager;

public interface IManagerDAO extends JpaRepository<Manager, Long> {

}

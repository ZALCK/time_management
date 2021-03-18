package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Module;

public interface IModuleDAO extends JpaRepository<Module, Long> {

}

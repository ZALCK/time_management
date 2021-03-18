package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Teacher;

public interface ITeacherDAO extends JpaRepository<Teacher, Long> {

}

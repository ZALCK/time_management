package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Student;

public interface IStudentDAO extends JpaRepository<Student, Long> {

}

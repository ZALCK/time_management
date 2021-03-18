package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Classroom;

public interface IClassroomDAO extends JpaRepository<Classroom, Long> {

}

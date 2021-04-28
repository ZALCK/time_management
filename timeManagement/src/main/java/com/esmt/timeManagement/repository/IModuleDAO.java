package com.esmt.timeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.model.Teacher;

public interface IModuleDAO extends JpaRepository<Module, Long> {
	public List<Module> findByClassroomAndTeacher (Classroom classroom, Teacher teacher);
}

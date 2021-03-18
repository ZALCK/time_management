package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Teacher;

public interface ITeacherService {
	public void create (Teacher teacher);
	public void update (Teacher teacher);
	public Teacher getTeacher (Long id);
	public void delete (Teacher teacher);
	public List<Teacher> getAll();
}

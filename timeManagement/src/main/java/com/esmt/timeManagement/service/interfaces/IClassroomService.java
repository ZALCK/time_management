package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Classroom;

public interface IClassroomService {
	public void create (Classroom classroom);
	public void update (Classroom classroom);
	public Classroom getClassroom (Long id);
	public void delete (Classroom classroom);
	public List<Classroom> getAll();
	public List<Classroom> getClassroomsByManager(Long id);
	public List<Classroom> getClassroomsByTeacher(Long id);
}

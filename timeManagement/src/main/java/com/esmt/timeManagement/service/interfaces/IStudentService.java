package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Student;

public interface IStudentService {
	public void create (Student student);
	public void update (Student student);
	public Student getStudent (Long id);
	public void delete (Student student);
	public void graduate (Long id);
	public List<Student> getAll();
}

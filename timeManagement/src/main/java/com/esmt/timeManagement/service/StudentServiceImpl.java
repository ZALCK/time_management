package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.service.interfaces.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentService iss;
	
	@Override
	public void create(Student student) {
		// TODO Auto-generated method stub
		iss.create(student);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		iss.update(student);
	}

	@Override
	public Student getStudent(Long id) {
		// TODO Auto-generated method stub
		return iss.getStudent(id);
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		iss.delete(student);
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return iss.getAll();
	}

}

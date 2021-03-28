package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.repository.IStudentDAO;
import com.esmt.timeManagement.service.interfaces.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentDAO isd;
	
	@Override
	public void create(Student student) {
		// TODO Auto-generated method stub
		isd.save(student);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		isd.saveAndFlush(student);
	}

	@Override
	public Student getStudent(Long id) {
		// TODO Auto-generated method stub
		return isd.getOne(id);
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub
		isd.delete(student);
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return isd.findAll();
	}

}

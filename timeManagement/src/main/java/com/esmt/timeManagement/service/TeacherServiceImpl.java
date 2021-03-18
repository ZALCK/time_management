package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Teacher;
import com.esmt.timeManagement.service.interfaces.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	ITeacherService its;
	
	@Override
	public void create(Teacher teacher) {
		// TODO Auto-generated method stub
		its.create(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		// TODO Auto-generated method stub
		its.update(teacher);
	}

	@Override
	public Teacher getTeacher(Long id) {
		// TODO Auto-generated method stub
		return its.getTeacher(id);
	}

	@Override
	public void delete(Teacher teacher) {
		// TODO Auto-generated method stub
		its.delete(teacher);
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return its.getAll();
	}

}

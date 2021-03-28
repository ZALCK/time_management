package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Teacher;
import com.esmt.timeManagement.repository.ITeacherDAO;
import com.esmt.timeManagement.service.interfaces.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	ITeacherDAO itd;
	
	@Override
	public void create(Teacher teacher) {
		// TODO Auto-generated method stub
		itd.save(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		// TODO Auto-generated method stub
		itd.saveAndFlush(teacher);
	}

	@Override
	public Teacher getTeacher(Long id) {
		// TODO Auto-generated method stub
		return itd.getOne(id);
	}

	@Override
	public void delete(Teacher teacher) {
		// TODO Auto-generated method stub
		itd.delete(teacher);
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return itd.findAll();
	}

}

package com.esmt.timeManagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.repository.IClassroomDAO;
import com.esmt.timeManagement.repository.IManagerDAO;
import com.esmt.timeManagement.repository.IModuleDAO;
import com.esmt.timeManagement.repository.ITeacherDAO;
import com.esmt.timeManagement.service.interfaces.IClassroomService;

@Service
public class ClassroomServiceImpl implements IClassroomService {

	@Autowired
	IClassroomDAO icd;
	@Autowired
	IManagerDAO imanagerdao;
	@Autowired
	ITeacherDAO itd;
	@Autowired
	IModuleDAO imoduledao;
	
	@Override
	public void create(Classroom classroom) {
		// TODO Auto-generated method stub
		icd.save(classroom);
	}

	@Override
	public void update(Classroom classroom) {
		// TODO Auto-generated method stub
		icd.saveAndFlush(classroom);
	}

	@Override
	public Classroom getClassroom(Long id) {
		// TODO Auto-generated method stub
		return icd.getOne(id);
	}

	@Override
	public void delete(Classroom classroom) {
		// TODO Auto-generated method stub
		icd.delete(classroom);
	}

	@Override
	public List<Classroom> getAll() {
		// TODO Auto-generated method stub
		return icd.findAll();
	}

	@Override
	public List<Classroom> getClassroomsByManager(Long id) {
		// TODO Auto-generated method stub
		return imanagerdao.getOne(id).getClassrooms();
	}

	@Override
	public List<Classroom> getClassroomsByTeacher(Long id) {
		// TODO Auto-generated method stub
		HashSet<Classroom> hashSetClassrooms = new HashSet<Classroom>();
		for (Module module : itd.getOne(id).getModules())
			hashSetClassrooms.add(module.getClassroom());
		List<Classroom> classrooms = new ArrayList<Classroom>(hashSetClassrooms);
		return classrooms;
	}

}

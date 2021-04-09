package com.esmt.timeManagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Role;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.model.Teacher;
import com.esmt.timeManagement.repository.IRoleDAO;
import com.esmt.timeManagement.repository.ITeacherDAO;
import com.esmt.timeManagement.service.interfaces.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	ITeacherDAO itd;
	@Autowired
	IRoleDAO ird;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void create(Teacher teacher) {
		// TODO Auto-generated method stub
		teacher.setEnabled(false);
		teacher.setPassword(passwordEncoder.encode(teacher.getEmail()));
		// Get Manager Role
		Role roleTeacher = ird.findByName(RoleList.TEACHER.toString());
		Set<Role> setA = new HashSet<>();
        setA.add(roleTeacher);
        
		teacher.setRoles(setA);
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

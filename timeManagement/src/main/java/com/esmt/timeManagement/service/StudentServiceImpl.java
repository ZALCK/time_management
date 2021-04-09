package com.esmt.timeManagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Role;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.repository.IRoleDAO;
import com.esmt.timeManagement.repository.IStudentDAO;
import com.esmt.timeManagement.service.interfaces.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentDAO isd;
	@Autowired
	IRoleDAO ird;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void create(Student student) {
		// TODO Auto-generated method stub
		student.setEnabled(false);
		student.setPassword(passwordEncoder.encode(student.getEmail()));
		// Get Manager Role
		Role roleStudent = ird.findByName(RoleList.STUDENT.toString());
		Set<Role> setA = new HashSet<>();
        setA.add(roleStudent);
        
		student.setRoles(setA);
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

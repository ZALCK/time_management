package com.esmt.timeManagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.model.Role;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.repository.IManagerDAO;
import com.esmt.timeManagement.repository.IRoleDAO;
import com.esmt.timeManagement.service.interfaces.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	IManagerDAO imd;
	@Autowired
	IRoleDAO ird;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void create(Manager manager) {
		// TODO Auto-generated method stub
		manager.setEnabled(false);
		manager.setPassword(passwordEncoder.encode(manager.getEmail()));
		// Get Manager Role
		Role roleManager = ird.findByName(RoleList.MANAGER.toString());
		Set<Role> setA = new HashSet<>();
        setA.add(roleManager);
        
		manager.setRoles(setA);
		imd.save(manager);
	}

	@Override
	public void update(Manager manager) {
		// TODO Auto-generated method stub
		imd.saveAndFlush(manager);
	}

	@Override
	public Manager getManager(Long id) {
		// TODO Auto-generated method stub
		return imd.getOne(id);
	}

	@Override
	public void delete(Manager manager) {
		// TODO Auto-generated method stub
		imd.delete(manager);;
	}

	@Override
	public List<Manager> getAll() {
		// TODO Auto-generated method stub
		return imd.findAll();
	}

}

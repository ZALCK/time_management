package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.repository.IManagerDAO;
import com.esmt.timeManagement.service.interfaces.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	IManagerDAO imd;
	
	@Override
	public void create(Manager manager) {
		// TODO Auto-generated method stub
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

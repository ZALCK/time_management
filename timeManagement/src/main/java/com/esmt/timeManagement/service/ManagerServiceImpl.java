package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.service.interfaces.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	IManagerService ims;
	
	@Override
	public void create(Manager manager) {
		// TODO Auto-generated method stub
		ims.create(manager);
	}

	@Override
	public void update(Manager manager) {
		// TODO Auto-generated method stub
		ims.update(manager);
	}

	@Override
	public Manager getManager(Long id) {
		// TODO Auto-generated method stub
		return ims.getManager(id);
	}

	@Override
	public void delete(Manager manager) {
		// TODO Auto-generated method stub
		ims.delete(manager);
	}

	@Override
	public List<Manager> getAll() {
		// TODO Auto-generated method stub
		return ims.getAll();
	}

}

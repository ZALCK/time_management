package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.service.interfaces.IModuleService;

@Service
public class ModuleServiceImpl implements IModuleService {

	@Autowired
	IModuleService ims;
	
	@Override
	public void create(Module module) {
		// TODO Auto-generated method stub
		ims.create(module);
	}

	@Override
	public void update(Module module) {
		// TODO Auto-generated method stub
		ims.update(module);
	}

	@Override
	public Module getModule(Long id) {
		// TODO Auto-generated method stub
		return ims.getModule(id);
	}

	@Override
	public void delete(Module module) {
		// TODO Auto-generated method stub
		ims.delete(module);
	}

	@Override
	public List<Module> getAll() {
		// TODO Auto-generated method stub
		return ims.getAll();
	}

}

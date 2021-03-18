package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Module;

public interface IModuleService {
	public void create (Module module);
	public void update (Module module);
	public Module getModule (Long id);
	public void delete (Module module);
	public List<Module> getAll();
}

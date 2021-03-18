package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Manager;

public interface IManagerService {
	public void create (Manager manager);
	public void update (Manager manager);
	public Manager getManager (Long id);
	public void delete (Manager manager);
	public List<Manager> getAll();
}

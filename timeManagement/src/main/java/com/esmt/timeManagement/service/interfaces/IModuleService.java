package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.model.Teacher;

public interface IModuleService {
	public void create (Module module);
	public void update (Module module);
	public Module getModule (Long id);
	public void delete (Module module);
	public List<Module> getAll();
	public List<Module> getModulesByTeacherAndClassroom (Classroom classroom, Teacher teacher);
}

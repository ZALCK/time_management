package com.esmt.timeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.model.Person;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.model.Session;
import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.service.interfaces.IClassroomService;
import com.esmt.timeManagement.service.interfaces.IManagerService;
import com.esmt.timeManagement.service.interfaces.IPersonService;

@Controller
@RequestMapping(value = "/classroom")
public class ClassroomController {

	@Autowired
	private IClassroomService ics;
	@Autowired
	private IPersonService ips;
	@Autowired
	private IManagerService ims;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddClassroom(Model model) {
		Classroom classroom = new Classroom();
		model.addAttribute("classroom", classroom);
		return "/classroom/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addClassroom(@ModelAttribute(value="classroom") Classroom classroom) {
		if (classroom.getManager() == null) {
			Manager managerConnected = ims.getManager(getCurrentPersonConnected().getId());
			classroom.setManager(managerConnected);
		}
		ics.create(classroom);
		return "redirect:/classroom/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Classroom classroom = ics.getClassroom(id);
		model.addAttribute("classroom", classroom);
		return "/classroom/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("classroom") Classroom classroom) {
		if (classroom.getManager() == null) {
			Manager managerConnected = ims.getManager(getCurrentPersonConnected().getId());
			classroom.setManager(managerConnected);
		}
		ics.update(classroom);
		return "redirect:/classroom/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model) {
		Classroom classroom = ics.getClassroom(id);
		ics.delete(classroom);
		return "redirect:/classroom/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listClassrooms(Model model) {
		//Create list classrooms based on user connected roles
		Person personConnected = getCurrentPersonConnected();
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.ADMIN.toString()))) {
			model.addAttribute("managers", ims.getAll());
			model.addAttribute("classrooms", ics.getAll());
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.MANAGER.toString()))) {
			model.addAttribute("classrooms", ics.getClassroomsByManager(personConnected.getId()));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.TEACHER.toString()))) {
			model.addAttribute("classrooms", ics.getClassroomsByTeacher(personConnected.getId()));
		}
		Classroom classroom = new Classroom();
		model.addAttribute("classroom", classroom);
		return "/classroom/dashboard";
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String listByClassroom(@PathVariable("id") long id, Model model) {
		//Create list classrooms based on user connected roles
		Classroom classroom = ics.getClassroom(id);
		model.addAttribute("students", classroom.getStudents());
		model.addAttribute("modules", classroom.getModules());
		model.addAttribute("classroom", classroom);
		
		// Define the value of the class attribute for the "student" and "module" objects of the forms
		Student student = new Student();
		student.setClassroom(classroom);
		Module module = new Module();
		module.setClassroom(classroom);
		Session meeting = new Session();
		model.addAttribute("student", student);
		model.addAttribute("module", module);
		model.addAttribute("meeting", meeting);
		model.addAttribute("classrooms", ics.getAll());
		return "/classroom/classroom-dashboard";
	}

	private Person getCurrentPersonConnected() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Person personConnected;
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			personConnected= ips.findByEmail(userDetails.getUsername());
			return personConnected;
		}
		return null;
	}
}

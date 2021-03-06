package com.esmt.timeManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.service.interfaces.IClassroomService;
import com.esmt.timeManagement.service.interfaces.IPersonService;
import com.esmt.timeManagement.service.interfaces.IStudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private IStudentService iss;
	@Autowired
	private IPersonService ips;
	@Autowired
	private IClassroomService ics;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddStudent(Model model) {
		Student student = new Student();
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("student", student);
		return "/student/ajout";
	}

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addStudent(HttpServletRequest request, @ModelAttribute(value="student") Student student) {
		Classroom classroom = ics.getClassroom(student.getClassroom().getId());
		student.setClassroom(classroom);
		iss.create(student);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Student student = iss.getStudent(id);
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("student", student);
		return "/student/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @ModelAttribute("student") Student student) {
		Student mergeStudentInfos = iss.getStudent(student.getId());
		mergeStudentInfos.setEmail(student.getEmail());
		mergeStudentInfos.setFirstname(student.getFirstname());
		mergeStudentInfos.setLastname(student.getLastname());
		mergeStudentInfos.setMatricule(student.getMatricule());
		mergeStudentInfos.setClassroom(student.getClassroom());
		iss.update(mergeStudentInfos);
		
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(HttpServletRequest request, @PathVariable("id") long id, Model model){
		Student student = iss.getStudent(id);
		iss.delete(student);
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/graduate/{id}", method = RequestMethod.GET)
	public String toGraduate(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
		iss.graduate(id);
		//Redirect to previous page
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(HttpServletRequest request, @PathVariable("id") long id, Model model){
		Student student = iss.getStudent(id);
		student.setPassword(student.getEmail());
		student.setEnabled(false);
		iss.update(student);
		
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(HttpServletRequest request, @PathVariable("id") long id, Model model){
		Student student = iss.getStudent(id);
		student.setEnabled(!student.isEnabled());
		iss.update(student);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listStudents(Model model) {
		List<Student> liste = iss.getAll();
		Student student = new Student();
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("student", student);
		model.addAttribute("students", liste);
		model.addAttribute("studentRole", ips.getRoleByName(RoleList.LEADER.toString()));
		return "/student/dashboard";
	}

}

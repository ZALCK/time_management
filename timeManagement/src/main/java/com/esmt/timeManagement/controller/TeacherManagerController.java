package com.esmt.timeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Teacher;
import com.esmt.timeManagement.service.interfaces.ITeacherService;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherManagerController {

	@Autowired
	private ITeacherService its;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddTeacher(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "/teacher/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addTeacher(@ModelAttribute(value="teacher") Teacher teacher) {
		its.create(teacher);
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Teacher teacher = its.getTeacher(id);
		model.addAttribute("teacher", teacher);
		return "/teacher/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("teacher") Teacher teacher) {
		Teacher mergeTeacherInfos = its.getTeacher(teacher.getId());
		mergeTeacherInfos.setEmail(teacher.getEmail());
		mergeTeacherInfos.setFirstname(teacher.getFirstname());
		mergeTeacherInfos.setLastname(teacher.getLastname());
		mergeTeacherInfos.setPhone(teacher.getPhone());
		its.update(mergeTeacherInfos);
		
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Teacher teacher = its.getTeacher(id);
		its.delete(teacher);
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(@PathVariable("id") long id, Model model){
		Teacher teacher = its.getTeacher(id);
		teacher.setPassword(teacher.getEmail());
		teacher.setEnabled(false);
		its.update(teacher);
		
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(@PathVariable("id") long id, Model model){
		Teacher teacher = its.getTeacher(id);
		teacher.setEnabled(!teacher.isEnabled());
		its.update(teacher);
		return "redirect:/teacher/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listTeachers(Model model) {
		List<Teacher> liste;
		liste = its.getAll();
		model.addAttribute("teachers", liste);
		return "/teacher/liste";
	}
	
}

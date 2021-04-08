package com.esmt.timeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Classroom;
import com.esmt.timeManagement.service.interfaces.IClassroomService;

@Controller
@RequestMapping(value = "/classroom")
public class ClassroomController {

	@Autowired
	private IClassroomService ics;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddClassroom(Model model) {
		Classroom classroom = new Classroom();
		model.addAttribute("classroom", classroom);
		return "/classroom/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addClassroom(@ModelAttribute(value="classroom") Classroom classroom) {
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
		ics.update(classroom);
		return "redirect:/classroom/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Classroom classroom = ics.getClassroom(id);
		ics.delete(classroom);
		return "redirect:/classroom/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listClassrooms(Model model) {
		List<Classroom> liste;
		liste = ics.getAll();
		model.addAttribute("classrooms", liste);
		return "/classroom/liste";
	}
	
}

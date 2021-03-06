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
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.service.interfaces.IModuleService;
import com.esmt.timeManagement.service.interfaces.ITeacherService;
import com.esmt.timeManagement.service.interfaces.IClassroomService;

@Controller
@RequestMapping(value = "/module")
public class ModuleController {

	@Autowired
	private IModuleService ims;
	@Autowired
	private IClassroomService ics;
	@Autowired
	private ITeacherService its;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddModule(Model model) {
		Module module = new Module();
		model.addAttribute("teachers", its.getAll());
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("module", module);
		return "/module/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addModule(HttpServletRequest request, @ModelAttribute(value="module") Module module) {
		Classroom classroom = ics.getClassroom(module.getClassroom().getId());
		module.setClassroom(classroom);
		ims.create(module);
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Module module = ims.getModule(id);
		model.addAttribute("teachers", its.getAll());
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("module", module);
		return "/module/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("module") Module module) {
		Module mergeModuleInfos = ims.getModule(module.getId());
		mergeModuleInfos.setName(module.getName());
		mergeModuleInfos.setHours(module.getHours());
		mergeModuleInfos.setClassroom(module.getClassroom());
		mergeModuleInfos.setTeacher(module.getTeacher());
		ims.update(mergeModuleInfos);

		return "redirect:/classroom/list/"+mergeModuleInfos.getClassroom().getId();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(HttpServletRequest request, @PathVariable("id") long id, Model model) {
		Module module = ims.getModule(id);
		ims.delete(module);
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listModule(Model model) {
		List<Module> liste = ims.getAll();
		Module module = new Module();
		model.addAttribute("classrooms", ics.getAll());
		model.addAttribute("module", module);
		model.addAttribute("modules", liste);
		return "/module/dashboard";
	}
	
}

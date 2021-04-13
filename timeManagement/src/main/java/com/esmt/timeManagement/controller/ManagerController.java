package com.esmt.timeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.service.interfaces.IManagerService;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	@Autowired
	private IManagerService ims;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddManager(Model model) {
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
		return "/manager/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addManager(@ModelAttribute(value="manager") Manager manager) {
		ims.create(manager);
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Manager manager = ims.getManager(id);
		model.addAttribute("manager", manager);
		return "/manager/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("manager") Manager manager) {
		Manager mergeManagerInfos = ims.getManager(manager.getId());
		mergeManagerInfos.setEmail(manager.getEmail());
		mergeManagerInfos.setFirstname(manager.getFirstname());
		mergeManagerInfos.setLastname(manager.getLastname());
		mergeManagerInfos.setPhone(manager.getPhone());
		ims.update(mergeManagerInfos);
		
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Manager manager = ims.getManager(id);
		ims.delete(manager);
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(@PathVariable("id") long id, Model model){
		Manager manager = ims.getManager(id);
		manager.setPassword(manager.getEmail());
		manager.setEnabled(false);
		ims.update(manager);
		
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(@PathVariable("id") long id, Model model){
		Manager manager = ims.getManager(id);
		manager.setEnabled(!manager.isEnabled());
		ims.update(manager);
		return "redirect:/manager/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listManagers(Model model) {
		List<Manager> liste;
		liste = ims.getAll();
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
		model.addAttribute("managers", liste);
		return "/manager/dashboard";
	}
	
}

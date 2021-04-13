package com.esmt.timeManagement.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esmt.timeManagement.model.Session;
import com.esmt.timeManagement.repository.SessionDAOImpl;
import com.esmt.timeManagement.annotations.AnnotationExclusionStrategy;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.service.interfaces.ISessionService;
import com.google.gson.*;
import com.esmt.timeManagement.service.interfaces.IModuleService;

@Controller
@RequestMapping(value = "/session")
public class SessionController {

	@Autowired
	private SessionDAOImpl sdi;
	@Autowired
	private ISessionService iss;
	@Autowired
	private IModuleService ims;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddSession(Model model) {
		Session meeting = new Session();
		meeting.setStartAt(Calendar.getInstance().getTime());
		model.addAttribute("modules", ims.getAll());
		model.addAttribute("meeting", meeting);
		return "/session/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addSession(@ModelAttribute(value="meeting") Session meeting) {
		Module module = ims.getModule(meeting.getModule().getId());
		meeting.setModule(module);
		iss.create(meeting);
		return "redirect:/session/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Session meeting = iss.getSession(id);
		model.addAttribute("modules", ims.getAll());
		model.addAttribute("meeting", meeting);
		return "/session/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("meeting") Session meeting) {
		Session mergeSessionInfos = iss.getSession(meeting.getId());
		mergeSessionInfos.setStartAt(meeting.getStartAt());
		mergeSessionInfos.setEndAt(meeting.getEndAt());
		mergeSessionInfos.setDetails(meeting.getDetails());
		mergeSessionInfos.setModule(meeting.getModule());
		iss.update(mergeSessionInfos);

		return "redirect:/session/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppressionWithGET(@PathVariable("id") long id, Model model){
		Session meeting = iss.getSession(id);
		iss.delete(meeting);
		return "redirect:/session/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String suppressionWithPOST(@RequestParam("id") long id, Model model){
		Session meeting = iss.getSession(id);
		iss.delete(meeting);
		return "redirect:/session/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listSession(Model model) {
		List<Session> liste = iss.getAll();
		Session meeting = new Session();
		meeting.setStartAt(Calendar.getInstance().getTime());
		model.addAttribute("modules", ims.getAll());
		model.addAttribute("meeting", meeting);
		model.addAttribute("meetings", liste);
		return "/session/dashboard";
	}

	@RequestMapping(value = "/rawlist", method = RequestMethod.GET)
	@ResponseBody
	public String rawlistSession(Model model) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		//return gson.toJson(iss.getAll());
		return gson.toJson(sdi.getAllSession());
	}
	
}

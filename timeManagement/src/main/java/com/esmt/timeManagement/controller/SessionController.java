package com.esmt.timeManagement.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esmt.timeManagement.model.Session;
import com.esmt.timeManagement.model.Status;
import com.esmt.timeManagement.model.Student;
import com.esmt.timeManagement.repository.SessionDAOImpl;
import com.esmt.timeManagement.annotations.AnnotationExclusionStrategy;
import com.esmt.timeManagement.model.Module;
import com.esmt.timeManagement.model.Person;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.service.interfaces.ISessionService;
import com.esmt.timeManagement.service.interfaces.IStudentService;
import com.google.gson.*;
import com.esmt.timeManagement.service.interfaces.IModuleService;
import com.esmt.timeManagement.service.interfaces.IPersonService;

@Controller
@RequestMapping(value = "/session")
public class SessionController {

	@Autowired
	private SessionDAOImpl sdi;
	@Autowired
	private ISessionService iss;
	@Autowired
	private IModuleService ims;
	@Autowired
	private IPersonService ips;
	@Autowired
	private IStudentService istudentservice;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAddSession(Model model) {
		Session meeting = new Session();
		meeting.setStartAt(Calendar.getInstance().getTime());
		model.addAttribute("modules", ims.getAll());
		model.addAttribute("meeting", meeting);
		return "/session/ajout";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addSession(HttpServletRequest request, @ModelAttribute(value="meeting") Session meeting) {
		Module module = ims.getModule(meeting.getModule().getId());
		meeting.setModule(module);
		meeting.setDetails(module.getName());
		iss.create(meeting);
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
		Session meeting = iss.getSession(id);
		model.addAttribute("modules", ims.getAll());
		model.addAttribute("meeting", meeting);
		return "redirect:" + request.getHeader("Referer");
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
		Person personConnected = getCurrentPersonConnected();
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.STUDENT.toString()))) {
			model.addAttribute("idClassroomStudent", istudentservice.getStudent(personConnected.getId()).getClassroom().getId());
		} else {
			List<Session> liste = iss.getAll();
			Session meeting = new Session();
			meeting.setStartAt(Calendar.getInstance().getTime());
			model.addAttribute("modules", ims.getAll());
			model.addAttribute("meeting", meeting);
			model.addAttribute("meetings", liste);
			model.addAttribute("idClassroomStudent", 0);
		}
		return "/session/dashboard";
	}
	
	@RequestMapping(value = "/textbook", method = RequestMethod.GET)
	public String listSessionsForTextbook(Model model) {
		Person personConnected = getCurrentPersonConnected();
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.TEACHER.toString()))) {
			model.addAttribute("meetings", iss.getSessionsToApproveByTeacher(personConnected.getId()));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.LEADER.toString()))) {
			Student student = istudentservice.getStudent(personConnected.getId());
			model.addAttribute("meetings", iss.getSessionsToApproveByClassroom(student.getClassroom().getId()));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.ADMIN.toString()))) {
			model.addAttribute("meetings", iss.getAll());
		}

		return "/session/textbook";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String changeStatus(@PathVariable("id") long id, Model model, HttpServletRequest request){
		Session session = iss.getSession(id);
		Person personConnected = getCurrentPersonConnected();
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.TEACHER.toString()))) {
			if (session.getStatus().equals(Status.IN_PROGRESS.toString())) {
				session.setStatus(Status.COMPLETED.toString());
			}
			if (session.getStatus().equals(Status.COMPLETED.toString())) {
				session.setStatus(Status.IN_PROGRESS.toString());
			}
		}
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.LEADER.toString()))) {
			if (session.getStatus().equals(Status.COMPLETED.toString())) {
				session.setStatus(Status.APPROVED.toString());
			} else if (session.getStatus().equals(Status.APPROVED.toString())) {
				session.setStatus(Status.COMPLETED.toString());
			}
		}
		iss.update(session);
		return "redirect:/session/textbook";
	}

	@RequestMapping(value = "/rawlist/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String rawlistSession(@PathVariable("id") long id, Model model) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		Person personConnected = getCurrentPersonConnected();
		if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.TEACHER.toString()))) {
			return gson.toJson(sdi.getSessionsByTeacher(personConnected.getId()));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.MANAGER.toString()))) {
			return gson.toJson(sdi.getSessionsByClassroom(id));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.STUDENT.toString()))) {
			return gson.toJson(sdi.getSessionsByClassroom(id));
		} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.ADMIN.toString()))) {
			return gson.toJson(sdi.getAllSession());
		}
		return null;
	}
	
	@RequestMapping(value = "/rawlistClass", method = RequestMethod.GET)
	@ResponseBody
	public String rawlistSessionByClassroom(Model model) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		return gson.toJson(sdi.getSessionsByTeacher(8));
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

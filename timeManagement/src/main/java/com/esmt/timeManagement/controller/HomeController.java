package com.esmt.timeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esmt.timeManagement.model.Person;
import com.esmt.timeManagement.model.RoleList;
import com.esmt.timeManagement.service.interfaces.IPersonService;

@Controller
public class HomeController {
	
	@Autowired
	IPersonService ips;
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHomePage() {
		if(getCurrentPersonConnected() != null) {
			Person personConnected = getCurrentPersonConnected();
			if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.ADMIN.toString()))) {
				return "redirect:/manager/list";
			} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.MANAGER.toString()))) {
				return "redirect:/classroom/list";
			} else if (personConnected.getRoles().contains(ips.getRoleByName(RoleList.TEACHER.toString()))) {
				return "redirect:/classroom/list";
			} else {
				return "redirect:/session/list";
			}
		}
		
		return "redirect:/login";
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication auth) {
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		return userDetails.toString();
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
	public String changePass(Authentication auth,
			@RequestParam("oldPass") String oldPass, 
			@RequestParam("newPass") String newPass, @RequestParam("repeatPass") String repeatPass) {
		System.out.println("Old pass : "+ oldPass + " New Pass : " + newPass + " Repeat Pass : "+ repeatPass);
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		Person person = ips.findByEmail(userDetails.getUsername());
		if( passwordEncoder.encode(oldPass) == person.getPassword() && newPass == repeatPass) {
			person.setPassword(newPass);
			ips.update(person);
		}
		
		return "redirect:/";
		
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

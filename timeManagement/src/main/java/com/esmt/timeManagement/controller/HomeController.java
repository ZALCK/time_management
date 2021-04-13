package com.esmt.timeManagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHomePage() {
		return "index";
	}
	
	@RequestMapping(value = "/fragment", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFragmentsPage() {
		return "/fragments/menus";
	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication auth) {
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		return userDetails.toString();
	}
}

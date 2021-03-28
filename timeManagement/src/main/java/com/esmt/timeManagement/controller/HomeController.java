package com.esmt.timeManagement.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esmt.timeManagement.model.Manager;
import com.esmt.timeManagement.model.Role;
import com.esmt.timeManagement.repository.IRoleDAO;
import com.esmt.timeManagement.service.interfaces.IManagerService;

@Controller
public class HomeController {
	
	@Autowired
	IManagerService ims;
	@Autowired
	IRoleDAO ird;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHomePage() {
		return "index";
	}
	
	@RequestMapping(value = "/createAdminAccount", method = RequestMethod.GET, headers = "Accept=application/json")
	public String createAdministrator() {
		
		Role roleAdmin = ird.findByName("ADMIN");
				
		Set<Role> setA = new HashSet<>();
        setA.add(roleAdmin);
		
		Manager admin = new Manager();
		admin.setFirstname("Root");
		admin.setLastname("ADMIN");
		admin.setPassword("admin");
		admin.setEmail("admin@admin.com");
		admin.setPhone(11111111);
		admin.setEnabled(true);
		admin.setRoles(setA);
		
		ims.create(admin);
		
		return "index";
	}
	
}

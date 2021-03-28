package com.esmt.timeManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.esmt.timeManagement.model.Person;
import com.esmt.timeManagement.repository.IPersonDAO;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private IPersonDAO personDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Person person = personDAO.findByEmail(username);
        
        if (person == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UserDetailsSecurity(person);
	}

}

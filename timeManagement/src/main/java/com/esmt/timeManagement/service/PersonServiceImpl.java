package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.exception.UserAlreadyExistException;
import com.esmt.timeManagement.model.Person;
import com.esmt.timeManagement.repository.IPersonDAO;
import com.esmt.timeManagement.service.interfaces.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	IPersonDAO ipd;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void create(Person person) {
		// TODO Auto-generated method stub
		ipd.save(person);
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		ipd.saveAndFlush(person);
	}

	@Override
	public Person getPerson(Long id) {
		// TODO Auto-generated method stub
		return ipd.getOne(id);
	}

	@Override
	public void delete(Person person) {
		// TODO Auto-generated method stub
		ipd.delete(person);;
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return ipd.findAll();
	}

	@Override
	public Person registerNewUserAccount(Person person) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(person.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + person.getEmail());
        }

//        user.setFirstName(person.getFirstName());
//        user.setLastName(person.getLastName());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
//        user.setEmail(person.getEmail());
//        user.setUsing2FA(person.isUsing2FA());
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return ipd.save(person);
	}
	
	private boolean emailExists(final String email) {
        return ipd.findByEmail(email) != null;
    }

}

package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.exception.UserAlreadyExistException;
import com.esmt.timeManagement.model.Person;

public interface IPersonService {
	public void create (Person person);
	public void update (Person person);
	public Person getPerson (Long id);
	public void delete (Person person);
	public List<Person> getAll();
	
	 Person registerNewUserAccount(Person person) throws UserAlreadyExistException;
}

package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Session;
import com.esmt.timeManagement.service.interfaces.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService {

	@Autowired
	ISessionService iss;
	
	@Override
	public void create(Session session) {
		// TODO Auto-generated method stub
		iss.create(session);
	}

	@Override
	public void update(Session session) {
		// TODO Auto-generated method stub
		iss.update(session);
	}

	@Override
	public Session getSession(Long id) {
		// TODO Auto-generated method stub
		return iss.getSession(id);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		iss.delete(session);
	}

	@Override
	public List<Session> getAll() {
		// TODO Auto-generated method stub
		return iss.getAll();
	}

}

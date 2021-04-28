package com.esmt.timeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esmt.timeManagement.model.Session;
import com.esmt.timeManagement.repository.ISessionDAO;
import com.esmt.timeManagement.service.interfaces.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService {

	@Autowired
	ISessionDAO isd;
	
	@Override
	public void create(Session session) {
		// TODO Auto-generated method stub
		isd.save(session);
	}

	@Override
	public void update(Session session) {
		// TODO Auto-generated method stub
		isd.saveAndFlush(session);
	}

	@Override
	public Session getSession(Long id) {
		// TODO Auto-generated method stub
		return isd.getOne(id);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		isd.delete(session);
	}

	@Override
	public List<Session> getAll() {
		// TODO Auto-generated method stub
		return isd.findAll();
	}

	@Override
	public List<Session> getSessionsToApproveByTeacher(long id) {
		// TODO Auto-generated method stub
		return isd.getSessionsToApproveByTeacher(id);
	}

	@Override
	public List<Session> getSessionsToApproveByClassroom(long id) {
		// TODO Auto-generated method stub
		return isd.getSessionsToApproveByClassroom(id);
	}

}

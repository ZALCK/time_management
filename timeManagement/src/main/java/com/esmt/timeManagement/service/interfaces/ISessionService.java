package com.esmt.timeManagement.service.interfaces;

import java.util.List;

import com.esmt.timeManagement.model.Session;

public interface ISessionService {
	public void create (Session session);
	public void update (Session session);
	public Session getSession (Long id);
	public void delete (Session session);
	public List<Session> getAll();
	List<Session> getSessionsToApproveByTeacher(long id);
	List<Session> getSessionsToApproveByClassroom(long id);
}

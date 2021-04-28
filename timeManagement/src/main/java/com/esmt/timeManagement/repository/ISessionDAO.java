package com.esmt.timeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esmt.timeManagement.model.Session;

public interface ISessionDAO extends JpaRepository<Session, Long> {
	@Query("SELECT s FROM Session s JOIN s.module mod JOIN mod.teacher teacher WHERE teacher.id = ?1")
	List<Session> getSessionsToApproveByTeacher(long id);
	
	@Query("SELECT s FROM Session s JOIN s.module mod JOIN mod.classroom classroom WHERE classroom.id = ?1")
	List<Session> getSessionsToApproveByClassroom(long id);
}

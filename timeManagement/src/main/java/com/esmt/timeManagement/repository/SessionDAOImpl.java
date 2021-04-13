package com.esmt.timeManagement.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.esmt.timeManagement.model.Event;

@Repository
public class SessionDAOImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Event> getAllSession() {
		List<Event> list = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = session.createQuery("SELECT s.id as id, s.details as title, DATE_FORMAT(s.startAt,'%Y-%m-%d') as start, DATE_FORMAT(s.endAt,'%Y-%m-%d') as end FROM Session s")
					.setResultTransformer(Transformers.aliasToBean(Event.class)).list();
			transaction.commit();
		} catch (Exception e) {
			list = null;
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		
		return list;
	}

}

package com.esmt.timeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.timeManagement.model.Session;

public interface ISessionDAO extends JpaRepository<Session, Long> {

}

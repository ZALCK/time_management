package com.esmt.timeManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modules")
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private int hours;
	
	@OneToOne(targetEntity = Classroom.class)
	private Classroom classroom;
	
	@OneToOne(targetEntity = Teacher.class)
	private Teacher teacher;
	
	@OneToMany(targetEntity = Session.class, mappedBy = "module", fetch = FetchType.LAZY)
	private List<Session> sessions;
	
	public Module() {
		super();
	}
	public Module(String name, int hours, Classroom classroom, Teacher teacher) {
		super();
		this.name = name;
		this.hours = hours;
		this.classroom = classroom;
		this.teacher = teacher;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", classroom=" + classroom + ", teacher=" + teacher + ", hours="
				+ hours + "]";
	}
	
}

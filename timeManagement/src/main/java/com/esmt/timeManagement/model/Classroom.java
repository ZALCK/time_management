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
@Table(name = "classrooms")
public class Classroom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private int year;
	
	@OneToOne
	private Manager manager;
	@OneToMany(targetEntity=Student.class, mappedBy = "classroom", fetch = FetchType.LAZY)
	private List<Student> students;
	@OneToMany(targetEntity=Module.class, mappedBy = "classroom", fetch = FetchType.LAZY)
	private List<Module> modules;

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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", year=" + year + "]";
	}

}

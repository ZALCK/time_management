package com.esmt.timeManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "ID")
public class Manager extends Person {
	
	private long phone;
	
	@OneToMany(targetEntity = Classroom.class)
	private List<Classroom> classrooms;
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public List<Classroom> getClassrooms() {
		return classrooms;
	}
	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", classrooms=" + classrooms + "]";
	}
	
}

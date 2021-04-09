package com.esmt.timeManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "ID")
public class Student extends Person {
	
	private String matricule;
	
	@OneToOne(targetEntity = Classroom.class)
	private Classroom classroom;

	@Column(name="matricule", unique=true)
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", matricule=" + matricule
				+ ", classroom=" + classroom + "]";
	}
	
}

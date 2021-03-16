package com.esmt.timeManagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date startAt;
	private Date endAt;
	private int duration;
	
	@OneToOne(targetEntity = Module.class)
	private Module module;
	
	public Session() {
		super();
	}
	public Session(Date startAt, Date endAt, int duration, Module module) {
		super();
		this.startAt = startAt;
		this.endAt = endAt;
		this.duration = duration;
		this.module = module;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", startAt=" + startAt + ", endAt=" + endAt + ", duration=" + duration
				+ ", module=" + module + "]";
	}
	

}

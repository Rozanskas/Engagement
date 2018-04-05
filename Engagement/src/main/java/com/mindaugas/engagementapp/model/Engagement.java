package com.mindaugas.engagementapp.model;

public class Engagement {
	
	private Integer studentId;
	private Integer employerId;
	private String date;
	

	public Engagement() {
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public Integer getEmployerId() {
		return employerId;
	}


	public void setEmployerId(Integer employerId) {
		this.employerId = employerId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	
}

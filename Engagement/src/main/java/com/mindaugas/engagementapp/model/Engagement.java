package com.mindaugas.engagementapp.model;

public class Engagement {
	
	private Integer studentId;
	private Integer employerId;
	private String date;
	private Integer interest;
	

	public Engagement() {
	}

	

	public Integer getInterest() {
		return interest;
	}



	public void setInterest(Integer interest) {
		this.interest = interest;
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

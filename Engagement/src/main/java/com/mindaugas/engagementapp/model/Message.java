package com.mindaugas.engagementapp.model;

public class Message {
	private Integer studentId;
	private Integer employerId;
	private String message;
	private String date;
	private String header;
	private String receiverEmail;
	private Integer id;
	
	public Message(){
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	
	public String getReceiverEmail() {
		return receiverEmail;
	}


	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}

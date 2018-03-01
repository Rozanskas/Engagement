package com.mindaugas.engagementapp.model;

public class SkillSet {

	private Integer skillSetId;
	private Integer studentId;
	private String university;
	private String course;
	private String personalProjects;
	private String grades;
	private String extra;
	
	public SkillSet(){}

	public Integer getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(Integer skillSetId) {
		this.skillSetId = skillSetId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPersonalProjects() {
		return personalProjects;
	}

	public void setPersonalProjects(String personalProjects) {
		this.personalProjects = personalProjects;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	
}

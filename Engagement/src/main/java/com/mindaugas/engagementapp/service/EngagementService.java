package com.mindaugas.engagementapp.service;

import java.util.List;

import com.mindaugas.engagementapp.model.Engagement;

public interface EngagementService {

	public List<Engagement>getStudentsEngaged(int empId);

	public Engagement getStudentEngagement(int studentId,int empId);

	public void updateEngagement(Engagement engagement);
	


	
}

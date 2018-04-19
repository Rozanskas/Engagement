package com.mindaugas.engagementapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindaugas.engagementapp.dao.EngagementDao;
import com.mindaugas.engagementapp.model.Engagement;

@Service
public class EngagementServiceImpl implements EngagementService {

	@Autowired
	EngagementDao engagementDao;

	@Override
	public List<Engagement> getStudentsEngaged(int empId) {
		return engagementDao.getStudentsEngaged(empId);
		
	}

	@Override
	public Engagement getStudentEngagement(int studentId,int empId) {
		return engagementDao.getStudentEngagement(studentId,empId);
	}

	@Override
	public void updateEngagement(Engagement engagement) {
		engagementDao.updateEngagement(engagement);
		
	}
	public List<Engagement> getEmployersEngaged(int studentId){
		return engagementDao.getEmployersEngaged(studentId);
	}

}

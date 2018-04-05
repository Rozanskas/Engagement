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

}

package com.mindaugas.engagementapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindaugas.engagementapp.exception.UserBlockedException;
import com.mindaugas.engagementapp.model.User;

public interface UserService {

	public static final Integer LOGIN_STATUS_ACTIVE = 1;
	public static final Integer LOGIN_STATUS_BLOCKED = 2;
	
	public static final Integer ROLE_EMPLOYER= 1;
	public static final Integer ROLE_STUDENT = 2;
	public static final Integer ROLE_ADMIN = 3;

	public void register(User u);

	public User login(String loginName, String password) throws UserBlockedException;

	public List<User> getStudentList();

	public void changeLoginStatus(int userId, int loginStatus);
	
	public boolean usernameExists(String username);
	
	public User findById(int userId);

	public void engageWithStudent(int empId, Integer studentId);

	public void undoStudent(int empId, Integer studentId);

	

}

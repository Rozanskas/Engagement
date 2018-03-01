package com.mindaugas.engagementapp.independenttest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mindaugas.engagementapp.config.SpringRootConfig;
import com.mindaugas.engagementapp.dao.UserDao;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.service.UserService;

public class TestUserServiceRegisterOperation {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserService userService = ctx.getBean(UserService.class);
		User user = new User();
		user.setName("Nitin");
		user.setEmail("rozaemail");
		user.setPassword("nonencrypt");
		user.setRole(userService.ROLE_ADMIN);
		user.setLoginStatus(userService.LOGIN_STATUS_ACTIVE);
		userService.register(user);
		System.out.println("DATA SAVED ");

	}

}

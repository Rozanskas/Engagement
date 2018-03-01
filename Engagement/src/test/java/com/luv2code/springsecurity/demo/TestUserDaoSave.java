package com.luv2code.springsecurity.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mindaugas.engagementapp.config.SpringRootConfig;
import com.mindaugas.engagementapp.dao.UserDao;
import com.mindaugas.engagementapp.model.User;

public class TestUserDaoSave {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = new User();
		user.setName("Minde");
		user.setPhone("444444");
		user.setEmail("rozaemail");
		user.setAddress("adresss");
		user.setLoginName("adas");
		user.setPassword("nonencrypt");
		user.setRole(1);
		user.setLoginStatus(1);
		userDao.save(user);
		System.out.println("DATA SAVED ");

	}

}

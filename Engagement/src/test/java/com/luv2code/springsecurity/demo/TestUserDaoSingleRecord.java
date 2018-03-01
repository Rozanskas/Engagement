package com.luv2code.springsecurity.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mindaugas.engagementapp.config.SpringRootConfig;
import com.mindaugas.engagementapp.dao.UserDao;
import com.mindaugas.engagementapp.model.User;

public class TestUserDaoSingleRecord {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = userDao.findById(4);
		System.out.println();
		System.out.println(user.getUser_id());
		System.out.println(user.getName());
		System.out.println(user.getAddress());
		System.out.println(user.getLoginName());

	}

}

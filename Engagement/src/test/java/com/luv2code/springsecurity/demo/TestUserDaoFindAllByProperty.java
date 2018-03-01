package com.luv2code.springsecurity.demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mindaugas.engagementapp.config.SpringRootConfig;
import com.mindaugas.engagementapp.dao.UserDao;
import com.mindaugas.engagementapp.model.User;

public class TestUserDaoFindAllByProperty {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		UserDao userDao = ctx.getBean(UserDao.class);
		List<User> users = userDao.findByProperty("name", "Mindaugas");
		for (User u : users) {
			System.out.println();
			System.out.println(u.getUser_id());
			System.out.println(u.getName());
			System.out.println(u.getAddress());
			System.out.println(u.getLoginName());
		}
	}

}

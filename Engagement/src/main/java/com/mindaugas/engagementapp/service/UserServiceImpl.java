package com.mindaugas.engagementapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mindaugas.engagementapp.dao.BaseDao;
import com.mindaugas.engagementapp.dao.UserDao;
import com.mindaugas.engagementapp.exception.UserBlockedException;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.rm.UserRowMapper;

@Service
public class UserServiceImpl extends BaseDao implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void register(User u) {
		userDao.save(u);
	}

	@Override
	public User login(String email, String password) throws UserBlockedException {
		String sql = "SELECT user_id, name, email, role,loginStatus "
				+ " FROM user WHERE email=:ln AND password=:pass";
		Map map = new HashMap();
		map.put("ln", email);
		map.put("pass", password);

		try {
			User user = getNamedParameterJdbcTemplate().queryForObject(sql, map, new UserRowMapper());

			if (user.getLoginStatus().equals(UserService.LOGIN_STATUS_BLOCKED)) {
				throw new UserBlockedException("Your Account has been blocked, Please Contact Admin");

			} else {
				return user;
			}

		} catch (EmptyResultDataAccessException ex) {

			return null;
		}

	}

	@Override
	public List<User> getStudentList() {
		return userDao.findByProperty("role", UserService.ROLE_STUDENT);
		
	}

	@Override
	public void changeLoginStatus(int userId, int loginStatus) {
		String sql ="UPDATE user SET loginStatus=:lst WHERE user_id=:uid ";
		Map map = new HashMap();
		map.put("lst", loginStatus);
		map.put("uid", userId);
		getNamedParameterJdbcTemplate().update(sql, map);

	}

	@Override
	public boolean usernameExists(String email) {
		String sql = "SELECT count(email) FROM user WHERE email=?";
		Integer count = getJdbcTemplate().queryForObject(sql, new String[]{email},Integer.class);
		if(count.equals(1)){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public User findById(int userId) {
		return userDao.findById(userId);
	}

	@Override
	public void engageWithStudent(int empId, Integer studentId) {
		userDao.engageWithStudent(empId,studentId);
		
	}

	

}

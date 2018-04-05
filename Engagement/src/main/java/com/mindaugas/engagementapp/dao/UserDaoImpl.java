package com.mindaugas.engagementapp.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.rm.UserRowMapper;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public void save(User u) {
		String sql = "Insert into user(name,email,password,role,loginStatus)"
				+ "VALUES (:name,:email,:password,:role,:loginStatus)";
		Map m = new HashMap();
		m.put("name", u.getName());
		m.put("email", u.getEmail());
		m.put("password", u.getPassword());
		m.put("role", u.getRole());
		m.put("loginStatus", u.getLoginStatus());

		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(m);

		super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
		Integer userId = kh.getKey().intValue();
		u.setUser_id(userId);

	}

	@Override
	public void update(User u) {
		String sql = "UPDATE user SET name=:name," + "email=:email,"
				+ "role=:role," + "loginStatus=:loginStatus " + " WHERE user_id=:userId";
		Map m = new HashMap();
		m.put("name", u.getName());
		m.put("email", u.getEmail());
		m.put("password", u.getPassword());
		m.put("role", u.getRole());
		m.put("loginStatus", u.getLoginStatus());
		m.put("userId", u.getUser_id());
		getNamedParameterJdbcTemplate().update(sql, m);

	}

	@Override
	public void delete(User u) {
		this.delete(u.getUser_id());

	}

	@Override
	public void delete(int userId) {
		String sql = "DELETE FROM user WHERE user_id=?";
		getJdbcTemplate().update(sql,userId);

	}

	@Override
	public User findById(int userId) {
		String sql = "SELECT user_id ,name, email,role,loginStatus"
				+" FROM user WHERE user_id=?";
		User user = getJdbcTemplate().queryForObject(sql, new UserRowMapper(),userId);
		return user;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT user_id ,name, email,role,loginStatus"
				+" FROM user";	
		List<User> users = getJdbcTemplate().query(sql, new UserRowMapper());
		
		return users;
	}

	@Override
	public List<User> findByProperty(String propName, Object propValue) {
		String sql = "SELECT user_id ,name, email,role,loginStatus"
				+" FROM user WHERE "+propName+"=?";			
		return getJdbcTemplate().query(sql, new UserRowMapper(),propValue);
	}

	@Override
	public void engageWithStudent(int empId, int studentId) {
		String sql = "Insert into engagement(student_id,employer_id,dateOfEngagement)"
				+ "VALUES (:studentID,:employerId,:date)";
		Map m = new HashMap();
		m.put("studentID", studentId);
		m.put("employerId", empId);
		m.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		SqlParameterSource ps = new MapSqlParameterSource(m);
		super.getNamedParameterJdbcTemplate().update(sql, ps);
		
		
	}

	@Override
	public void undoStudent(int empId, int studentId) {
		String sql = "DELETE FROM engagement WHERE student_id=?";
		getJdbcTemplate().update(sql,studentId);
	}

	
}

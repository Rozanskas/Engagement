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

import com.mindaugas.engagementapp.model.Message;
import com.mindaugas.engagementapp.model.SkillSet;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.rm.MessageRowMapper;
import com.mindaugas.engagementapp.rm.SkillSetRowMapper;
import com.mindaugas.engagementapp.rm.UserRowMapper;

@Repository
public class MessageDaoImpl extends BaseDao implements MessageDao {

	@Override
	public List<Message> empGetMessages(Integer empId) {
		String sql = "SELECT id,studentId ,employerId, message,date, header,receiverEmail,senderEmail "
				+" FROM message WHERE employerId=?";	
		List<Message> messages = getJdbcTemplate().query(sql, new MessageRowMapper(),empId);
		return messages;
	}

	@Override
	public void empSendMessage(Message message) {
		String sql = "Insert into message(studentId,employerId,message,date,header,receiverEmail,senderEmail )"
				+ "VALUES (:studentId,:employerId,:message,:date,:header,:receiverEmail,:senderEmail )";
		Map m = new HashMap();
		m.put("studentId", message.getStudentId());
		m.put("employerId", message.getEmployerId());
		m.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		m.put("message", message.getMessage());
		m.put("header", message.getHeader());
		m.put("receiverEmail", message.getReceiverEmail());
		m.put("senderEmail", message.getSenderEmail());
		KeyHolder kh = new GeneratedKeyHolder();
		SqlParameterSource ps = new MapSqlParameterSource(m);
		super.getNamedParameterJdbcTemplate().update(sql, ps,kh);
		Integer id = kh.getKey().intValue();
		message.setId(id);
		
	}

	@Override
	public List<Message> stGetMessages(Integer studentId) {
		String sql = "SELECT id,studentId ,employerId, message,date, header,receiverEmail,senderEmail "
				+" FROM message WHERE studentId=?";	
		List<Message> messages = getJdbcTemplate().query(sql, new MessageRowMapper(),studentId);
		return messages;
	}

	

}

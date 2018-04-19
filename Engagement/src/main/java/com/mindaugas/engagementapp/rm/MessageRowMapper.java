package com.mindaugas.engagementapp.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindaugas.engagementapp.model.Message;

public class MessageRowMapper implements RowMapper<Message>{

	@Override
	public Message mapRow(ResultSet rs, int i) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("id"));
		message.setStudentId(rs.getInt("studentId"));
		message.setEmployerId(rs.getInt("employerId"));
		message.setMessage(rs.getString("message"));
		message.setDate(rs.getString("date"));
		message.setHeader(rs.getString("header"));
		message.setReceiverEmail(rs.getString("receiverEmail"));
		message.setSenderEmail(rs.getString("senderEmail"));
		
		return message;
	}

}

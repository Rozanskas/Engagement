package com.mindaugas.engagementapp.dao;

import java.util.List;

import com.mindaugas.engagementapp.model.Message;
import com.mindaugas.engagementapp.model.User;


public interface MessageDao {

	public List<Message> empGetMessages(Integer empId);
	public void empSendMessage(Message message);
}

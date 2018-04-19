package com.mindaugas.engagementapp.service;

import java.util.List;

import com.mindaugas.engagementapp.model.Message;
import com.mindaugas.engagementapp.model.User;

public interface MessageService {

	public List<Message> getMessages(Integer employerId);
	public void empSendMessage(Message message);
	public List<Message> getMessagesSt(Integer studentId);
	

	
}

package com.mindaugas.engagementapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindaugas.engagementapp.dao.MessageDao;
import com.mindaugas.engagementapp.model.Message;
import com.mindaugas.engagementapp.model.User;
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao ;

	@Override
	public List<Message> getMessages(Integer employerId) {
		
		return messageDao.empGetMessages(employerId);
	}

	@Override
	public void empSendMessage(Message message) {
		messageDao.empSendMessage(message);
		
	}

	@Override
	public List<Message> getMessagesSt(Integer studentId) {
		return messageDao.stGetMessages(studentId);
	}

	
	

}

package com.mindaugas.engagementapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindaugas.engagementapp.model.Message;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.service.MessageService;
import com.mindaugas.engagementapp.service.UserService;

@Controller
public class MessageController {

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/employer/messageForm" })
	public String messageForm(Model model, @RequestParam int studentId, HttpSession session) {
		session.setAttribute("studentId", studentId);
		Message message = new Message();
		model.addAttribute("message", message);
		return "messageForm";
	}

	@RequestMapping(value = { "/employer/sendMessage" })
	public String sendMessage(@ModelAttribute("message") Message message, Model model, HttpSession session) {

		try {
			Integer studentId = (Integer) session.getAttribute("studentId");
			message.setStudentId(studentId);
			Integer empId = (Integer) session.getAttribute("userId");
			message.setEmployerId(empId);
			User student = userService.findById(studentId);
			message.setReceiverEmail(student.getEmail());
			messageService.empSendMessage(message);
			
			return "redirect:messages?act=success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "Failed to send message, try again.");
			return "messageForm";
		}
	}

	@RequestMapping(value = { "employer/messages" })
	public String getMessages(Model model, HttpSession session) {
		Integer empId = (Integer) session.getAttribute("userId");
		List<Message> messages = messageService.getMessages(empId);
		model.addAttribute("messages", messages);
		return "empMessages";

	}

}

package com.mindaugas.engagementapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindaugas.engagementapp.command.LoginCommand;
import com.mindaugas.engagementapp.command.UserCommand;
import com.mindaugas.engagementapp.exception.UserBlockedException;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("command", new LoginCommand());
		return "index";
	}

	@PostMapping(value = { "/login" })
	public String login(@ModelAttribute("command") LoginCommand cmd, Model model, HttpSession session) {
		try {
			User loggedInUser = userService.login(cmd.getEmail(), cmd.getPassword());
			if (loggedInUser == null) {
				// Failed
				model.addAttribute("err", "Login Failed : Please enter valid Credentials");
				return "index";
			} else {
				// Success
				if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
					addUserInSession(loggedInUser, session);
					return "redirect:admin/dashboard";
				} else if (loggedInUser.getRole().equals(UserService.ROLE_STUDENT)) {
					addUserInSession(loggedInUser, session);
					return "redirect:student/dashboard";
				}  else if (loggedInUser.getRole().equals(UserService.ROLE_EMPLOYER)) {
					addUserInSession(loggedInUser, session);
					return "redirect:employer/dashboard";
				}else {
					model.addAttribute("err", "Invalid User Role");
					return "index";
				}

			}
		} catch (UserBlockedException e) {
			model.addAttribute("err", e.getMessage());
			return "index";
		}

	}

	@RequestMapping(value = { "/student/dashboard" })
	public String studentDashboard() {
		return "dashboard_student";
	}
	@RequestMapping(value = { "/employer/dashboard" })
	public String employerDashboard() {
		return "dashboard_employer";
	}
	
	@RequestMapping(value = { "/employer/student_list" })
	public String studentList(Model model) {
		model.addAttribute("studentList",userService.getStudentList());
		return "student_list";
	}


	@RequestMapping(value = { "logout" })
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index?act=lo";
	}

	@RequestMapping(value = { "/admin/dashboard" })
	public String adminDashboard() {
		return "dashboard_admin";
	}
	@RequestMapping(value = { "/admin/users" })
	public String getUserList(Model model) {
		model.addAttribute("userList",userService.getStudentList());
		return "users";
	}

	@RequestMapping(value = { "/register" })
	public String registerUser(@ModelAttribute("command")UserCommand cmd,Model model) {
		try {
			User user = cmd.getUser();
			user.setRole(cmd.getUser().getRole());
			user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
			userService.register(user);
			return "redirect:index?act=reg";
		} catch (DuplicateKeyException e) {
			model.addAttribute("err", "UserName already taken, enter another UserName");
			return "reg_form";
		}
	}

	@RequestMapping(value = "/reg_form")
	public String registrationForm(Model model) {
		UserCommand userCommand = new UserCommand();
		model.addAttribute("command", userCommand);
		return "reg_form";
	}
	@RequestMapping(value = "/admin/change_status")
	@ResponseBody
	public String changeStatus(@RequestParam Integer userId,@RequestParam Integer loginStatus) {
		try {
			userService.changeLoginStatus(userId, loginStatus);
			return "Success: Status changed";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error: Unable to change status";
		}
		
	}
	
	@RequestMapping(value = "/check_avail")
	@ResponseBody
	public String checkAvailability(@RequestParam String username) {
		if(userService.usernameExists(username)){
			return "Username is taken. PLease Choose another one";
		}else {
			return "Username can be used";
		}
	}

	private void addUserInSession(User user, HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getUser_id());
		session.setAttribute("role", user.getRole());
	}

}

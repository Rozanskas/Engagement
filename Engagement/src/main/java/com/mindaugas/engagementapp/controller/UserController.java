package com.mindaugas.engagementapp.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.mindaugas.engagementapp.model.Engagement;
import com.mindaugas.engagementapp.model.SkillSet;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.service.EngagementService;
import com.mindaugas.engagementapp.service.SkillSetService;
import com.mindaugas.engagementapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	SkillSetService skillSetService;
	@Autowired
	EngagementService engagementService;

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
				} else if (loggedInUser.getRole().equals(UserService.ROLE_EMPLOYER)) {
					addUserInSession(loggedInUser, session);
					return "redirect:employer/dashboard";
				} else {
					model.addAttribute("err", "Invalid User Role");
					return "index";
				}

			}
		} catch (UserBlockedException e) {
			model.addAttribute("err", e.getMessage());
			return "index";
		}

	}

	
	@RequestMapping(value = { "/employer/e_update" })
	public String eProfileUpdate(@RequestParam int studentId,@ModelAttribute("engagement") Engagement engagement,Model model,HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
	    try {
	    	engagement.setEmployerId(empId);
	    	engagement.setStudentId(studentId);
			engagementService.updateEngagement(engagement);
			return "redirect:engaging_candidates?act=ed";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err","Failed to save changes ");
			return "emp_e_profile";
		}
		
	}
	
	@RequestMapping(value = { "/employer/e_profile" })
	public String eProfile(@RequestParam int studentId,Model model,HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		Engagement engagement =engagementService.getStudentEngagement(studentId,empId);
		model.addAttribute("engagement", engagement);
		return "emp_e_profile";
	}

	@RequestMapping(value = { "/student/dashboard" })
	public String studentDashboard() {
		return "dashboard_student";
	}

	@RequestMapping(value = { "/employer/dashboard" })
	public String employerDashboard() {
		return "dashboard_employer";
	}

	@RequestMapping(value = { "/employer/engaging_candidates" })
	public String engagingCandidates(Model model, HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		List<User> studentList = new ArrayList<>();

		try {
			List<Engagement> engagementList = engagementService.getStudentsEngaged(empId);
			for (Engagement engagement : engagementList) {
				User student = userService.findById(engagement.getStudentId());
				student.setSkillSet(skillSetService.findSkillSetByStudentId(student.getUser_id()));
				studentList.add(student);
			}
		} catch (Exception e) {

		}
		model.addAttribute("studentList", studentList);
		return "engaging_candidates";
	}

	@RequestMapping(value = { "/employer/student_list" })
	public String studentList(Model model, HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		List<User> studentList = userService.getStudentList();
		model.addAttribute("studentList", studentList);
		for (User student : studentList) {

			try {
				student.setSkillSet(skillSetService.findSkillSetByStudentId(student.getUser_id()));
				List<Engagement> engagementList = engagementService.getStudentsEngaged(empId);
				model.addAttribute("engagementList", engagementList);
			} catch (Exception e) {
				return "student_list";

			}
		}
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
		model.addAttribute("userList", userService.getStudentList());
		return "users";
	}

	@RequestMapping(value = { "/register" })
	public String registerUser(@ModelAttribute("command") UserCommand cmd, Model model) {
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
	public String changeStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
		try {
			userService.changeLoginStatus(userId, loginStatus);
			return "Success: Status changed";
		} catch (Exception e) {

			return "Error: Unable to change status";
		}

	}

	@RequestMapping(value = "/check_avail")
	@ResponseBody
	public String checkAvailability(@RequestParam String username) {
		if (userService.usernameExists(username)) {
			return "Email is already in use. Please enter another email";
		} else {
			return "Email can be used";
		}
	}

	private void addUserInSession(User user, HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getUser_id());
		session.setAttribute("role", user.getRole());

	}

	@RequestMapping(value = "/employer/student_search")
	public String studentSearch(Model model, HttpSession session, @RequestParam("uni") String uni,
			@RequestParam("course") String course, @RequestParam("skill") String skills,
			@RequestParam("grade") String grade, @RequestParam("pp") String pp, @RequestParam("extra") String extra) {

		int empId = (Integer) session.getAttribute("userId");
		try {
			List<Engagement> engagementList = engagementService.getStudentsEngaged(empId);
			model.addAttribute("engagementList", engagementList);
		} catch (Exception e) {

		}
		List<SkillSet> skillSetList = skillSetService.findSkillSetByProperty(uni, course, pp, skills, grade, extra);
		List<User> studentList = new ArrayList<>();
		for (SkillSet skill : skillSetList) {
			User tempStudent = userService.findById(skill.getStudentId());
			tempStudent.setSkillSet(skill);

			studentList.add(tempStudent);
		}
		model.addAttribute("studentList", studentList);

		return "student_list";
	}

	@RequestMapping(value = "/employer/engage")
	@ResponseBody
	public String engage(@RequestParam("cid") Integer studentId, HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		userService.engageWithStudent(empId, studentId);
		return "Student was successfully added to Engagement list";
	}

	@RequestMapping(value = "/employer/undo")
	@ResponseBody
	public String undo(@RequestParam("cid") Integer studentId, HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		userService.undoStudent(empId, studentId);
		return "Student was successfully removed from Engagement list";
	}
	
	@RequestMapping(value = { "/employer/updateProfile" })
	public String updateProfile(Model model,HttpSession session) {
		int empId = (Integer) session.getAttribute("userId");
		User user = userService.findById(empId);
		model.addAttribute("user",user);
		return "updateEmpProfile";
	}
	
	@RequestMapping(value = { "/employer/saveUpdate" })
	public String saveUpdate(@ModelAttribute ("user")User user,HttpSession session,Model model) {
		int empId = (Integer) session.getAttribute("userId");
		userService.updateUserName(user.getName(),empId);
		session.setAttribute("user", user);
		model.addAttribute("success","Changes saved successfully");
		return "updateEmpProfile";
	}
	
	@RequestMapping(value = { "/student/engaging_employers" })
	public String getEmployersInEngagement(HttpSession session,Model model) {
		int studentId = (Integer) session.getAttribute("userId");
		List<Engagement> engagements = engagementService.getEmployersEngaged(studentId);
		List<User>employers=new ArrayList<>();
		for(Engagement e: engagements){
			User userId =userService.findById(e.getEmployerId());
			employers.add(userId);
		}
		model.addAttribute("employers",employers);
		return "employers_engaging";
	}
	
	@RequestMapping(value = { "/student/e_profile" })
	public String engagementProfile(@RequestParam("empId") Integer empId,HttpSession session,Model model) {
		int studentId = (Integer) session.getAttribute("userId");
		Engagement engagement = engagementService.getStudentEngagement(studentId, empId);
		model.addAttribute("engagement",engagement);
		return "st_e_profile";
	}
	
	@RequestMapping(value = { "/about" })
	public String about() {
		return "about";
	}
		
		
}

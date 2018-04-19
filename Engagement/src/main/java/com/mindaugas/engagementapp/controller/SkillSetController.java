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
import com.mindaugas.engagementapp.dao.SkillSetDao;
import com.mindaugas.engagementapp.exception.UserBlockedException;
import com.mindaugas.engagementapp.model.SkillSet;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.service.SkillSetService;
import com.mindaugas.engagementapp.service.UserService;

@Controller
public class SkillSetController {

	@Autowired
	SkillSetService skillSetService;

	@RequestMapping(value = { "/student/skill_set" })
	public String studentSkill(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		try {

			model.addAttribute("skillSet", skillSetService.findSkillSetByStudentId(userId));
			return "student_skill_set";
		} catch (Exception e) {

			return "student_skill_set";
		}
	}
	
	
	@RequestMapping(value="/student/edit_skill")
	public String prepareEditForm(Model model, HttpSession session, @RequestParam("cid")Integer skillSetId){
		session.setAttribute("skillSetId", skillSetId);
	    SkillSet skillSet = skillSetService.findById(skillSetId);
	    model.addAttribute("skillSet",skillSet);
		return "skill_form";
	}
	
	@RequestMapping(value="/student/del_skill")
	public String deleteSkillset(@RequestParam("cid")Integer skillSetId,HttpSession session){
		skillSetService.delete(skillSetId);
		session.removeAttribute("skillSetId");
		return "redirect:skill_set?act=del";
	}
	
	@RequestMapping(value = { "/student/skill_form" })
	public String addSkillForm(Model model) {
		model.addAttribute("skillSet", new SkillSet());
		return "skill_form";
	}

	@RequestMapping(value = "/student/save_skill_set")
	public String saveSkillForm(@ModelAttribute SkillSet skillSet, Model model, HttpSession session) {
		Integer skillSetId = (Integer) session.getAttribute("skillSetId");
		Integer userId = (Integer) session.getAttribute("userId");
		if (skillSetId == null) {
			// Save
			try {
				skillSet.setStudentId(userId);
				skillSetService.save(skillSet);
				return "redirect:skill_set?act=sv";
			} catch (Exception e) {
				model.addAttribute("err", "Failed to save Skill Set");
				return "skill_form";
			}
		} else {
			// Update
			try {
				skillSet.setSkillSetId(skillSetId);
				skillSet.setStudentId(userId);
				skillSetService.update(skillSet);
				return "redirect:skill_set?act=ed";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("err", "Failed to Edit Skill Set");
				return "skill_form";
			}
		}

	}

}

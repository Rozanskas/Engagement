package com.mindaugas.engagementapp.dao;

import java.util.List;

import com.mindaugas.engagementapp.model.SkillSet;
import com.mindaugas.engagementapp.model.User;


public interface SkillSetDao {

	public SkillSet findSkillSetByStudentId(int studentId);
	
	public void save(SkillSet skillSet);
	
	public void update(SkillSet skillset);
	
	public List<User> findStudentByProperty(String propName,Object propValue);

	public SkillSet findById(int skillSetId);

	public void delete(int skillSetId);
	
}
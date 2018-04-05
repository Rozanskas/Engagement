package com.mindaugas.engagementapp.service;

import java.util.List;

import com.mindaugas.engagementapp.model.SkillSet;

public interface SkillSetService {

	public SkillSet findSkillSetByStudentId(int studentId);
	
	public void save(SkillSet skillSet);
	
	public void update(SkillSet skillSet);
	
	public SkillSet findById(int skillSetId);
	
	public void delete(int skillSetId);
	
	public List<SkillSet> findSkillSetByProperty(String uni,String course,String pp,String skill,String grade,String extra);

	
}

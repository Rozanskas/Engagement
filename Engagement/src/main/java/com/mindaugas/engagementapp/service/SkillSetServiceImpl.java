package com.mindaugas.engagementapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindaugas.engagementapp.dao.SkillSetDao;
import com.mindaugas.engagementapp.model.SkillSet;
@Service
public class SkillSetServiceImpl implements SkillSetService {
	
	@Autowired
	private SkillSetDao skillSetDao;

	@Override
	public SkillSet findSkillSetByStudentId(int studentId) {
		return skillSetDao.findSkillSetByStudentId(studentId);
		
	}

	@Override
	public void save(SkillSet skillSet) {
		skillSetDao.save(skillSet);
		
	}

	@Override
	public void update(SkillSet skillSet) {
		skillSetDao.update(skillSet);
		
	}

	@Override
	public SkillSet findById(int skillSetId) {
		return skillSetDao.findById(skillSetId);
	}

	@Override
	public void delete(int skillSetId) {
		
		skillSetDao.delete(skillSetId);
	}

	@Override
	public List<SkillSet> findSkillSetByProperty(String txt) {
		return skillSetDao.findSkillSetByProperty(txt);
	}
	

}

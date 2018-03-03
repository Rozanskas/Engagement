package com.mindaugas.engagementapp.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindaugas.engagementapp.model.SkillSet;

public class SkillSetRowMapper implements RowMapper<SkillSet>{

	@Override
	public SkillSet mapRow(ResultSet rs, int i) throws SQLException {
		SkillSet skillSet = new SkillSet();
		skillSet.setSkillSetId(rs.getInt("skill_set_id"));
		  skillSet.setStudentId(rs.getInt("student_id"));
		  skillSet.setUniversity(rs.getString("university"));
		  skillSet.setCourse(rs.getString("course"));
		  skillSet.setPersonalProjects(rs.getString("personal_projects"));
		  skillSet.setGrades(rs.getString("grades"));
		  skillSet.setExtra(rs.getString("extra"));
		  skillSet.setSkill(rs.getString("skill"));
	
		return skillSet;
	}
}

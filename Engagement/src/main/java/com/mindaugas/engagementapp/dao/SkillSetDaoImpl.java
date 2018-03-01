package com.mindaugas.engagementapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mindaugas.engagementapp.model.SkillSet;
import com.mindaugas.engagementapp.model.User;
import com.mindaugas.engagementapp.rm.SkillSetRowMapper;
import com.mindaugas.engagementapp.rm.UserRowMapper;

@Repository
public class SkillSetDaoImpl extends BaseDao implements SkillSetDao {

	@Override
	public SkillSet findSkillSetByStudentId(int studentId) {
		String sql = "SELECT skill_set_id, student_id ,university,course,personal_projects,"
				+ "grades,extra FROM skill_set  WHERE student_id=?";
		return getJdbcTemplate().queryForObject(sql, new SkillSetRowMapper(),studentId);
	}
	@Override
	public void save(SkillSet skillSet) {
		String sql = "Insert INTO skill_set (skill_set_id,student_id,university, course, personal_projects, grades,extra)"
				+ " VALUES(:skillId,:studentId,:university, :course, :personalP, :grades,:extra)";
		Map m = new HashMap();
		m.put("skillId", skillSet.getSkillSetId());
		m.put("studentId", skillSet.getStudentId());
		m.put("university", skillSet.getUniversity());
		m.put("course", skillSet.getCourse());
		m.put("personalP", skillSet.getPersonalProjects());
		m.put("grades",skillSet.getGrades());
		m.put("extra", skillSet.getExtra());
		
		SqlParameterSource ps = new MapSqlParameterSource(m);
		KeyHolder kh = new GeneratedKeyHolder();
		getNamedParameterJdbcTemplate().update(sql, ps, kh);
		skillSet.setSkillSetId(kh.getKey().intValue());
	}
	

	@Override
	public void update(SkillSet skillSet) {
		String sql ="Update skill_set SET university=:university, course=:course, personal_projects=:personalP, grades=:grades,extra=:extra "
				+ "WHERE skill_set_id=:skillId";

		Map m = new HashMap();
		m.put("skillId", skillSet.getSkillSetId());
		m.put("university", skillSet.getUniversity());
		m.put("course", skillSet.getCourse());
		m.put("personalP", skillSet.getPersonalProjects());
		m.put("grades",skillSet.getGrades());
		m.put("extra", skillSet.getExtra());
		
		getNamedParameterJdbcTemplate().update(sql, m);
	}

	@Override
	public List<SkillSet> findSkillSetByProperty(String txt) {
		String sql = "SELECT skill_set_id, student_id, university, course, personal_projects, grades, extra "
				+ "FROM skill_set WHERE  (university LIKE '%" + txt + "%' OR course LIKE '%" + txt + "%'"
				+ " OR personal_projects LIKE '%" + txt + "%' OR grades LIKE '%" + txt + "%'" + "OR extra LIKE '%" + txt
				+ "%')";
		return getJdbcTemplate().query(sql, new SkillSetRowMapper());
	}
	@Override
	public SkillSet findById(int skillSetId) {
		String sql = "SELECT skill_set_id, student_id ,university,course,personal_projects,"
				+ "grades,extra FROM skill_set  WHERE skill_set_id=?";
		return getJdbcTemplate().queryForObject(sql, new SkillSetRowMapper(),skillSetId);
		
	}
	@Override
	public void delete(int skillSetId) {
		String sql = "DELETE FROM skill_set WHERE skill_set_id=?";
		getJdbcTemplate().update(sql, skillSetId);
	}

}

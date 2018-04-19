package com.mindaugas.engagementapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mindaugas.engagementapp.model.Engagement;
import com.mindaugas.engagementapp.rm.EngagementRowMapper;

@Repository
public class EngagementDaoImpl extends BaseDao implements EngagementDao {

	@Override
	public List<Engagement> getStudentsEngaged(int empId) {
		
			String sql = "SELECT student_id,employer_id,dateOfEngagement,interest "
					+" FROM engagement WHERE employer_id=?";
			return getJdbcTemplate().query(sql, new EngagementRowMapper(),empId);
			
 
	}

	@Override
	public Engagement getStudentEngagement(int studentId,int empId) {
		String sql = "SELECT student_id,employer_id,dateOfEngagement,interest "
				+" FROM engagement WHERE student_id=? AND employer_id=?";
		return getJdbcTemplate().queryForObject(sql, new EngagementRowMapper(),studentId,empId);
	}

	@Override
	public void updateEngagement(Engagement engagement) {
		String sql = "Update engagement SET interest=:interest"
				+ " WHERE student_id=:studentId AND employer_id=:employerId";

		Map map = new HashMap();
		map.put("interest", engagement.getInterest());
		map.put("studentId", engagement.getStudentId());
		map.put("employerId", engagement.getEmployerId());
		
		getNamedParameterJdbcTemplate().update(sql, map);
		
	}

}

package com.mindaugas.engagementapp.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mindaugas.engagementapp.model.Engagement;
import com.mindaugas.engagementapp.rm.EngagementRowMapper;

@Repository
public class EngagementDaoImpl extends BaseDao implements EngagementDao {

	@Override
	public List<Engagement> getStudentsEngaged(int empId) {
		
			String sql = "SELECT student_id,employer_id,dateOfEngagement "
					+" FROM engagement WHERE employer_id=?";
			return getJdbcTemplate().query(sql, new EngagementRowMapper(),empId);
			
 
	}

}

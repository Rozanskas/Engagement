package com.mindaugas.engagementapp.rm;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mindaugas.engagementapp.model.Engagement;


public class EngagementRowMapper implements RowMapper<Engagement>{

	@Override
	public Engagement mapRow(ResultSet rs, int i) throws SQLException {
		Engagement engagement= new Engagement();
		
		engagement.setEmployerId(rs.getInt("employer_id"));
		engagement.setStudentId(rs.getInt("student_id"));
		engagement.setDate(rs.getString("dateOfEngagement"));
		engagement.setInterest(rs.getInt("interest"));
		return engagement;
	}

}

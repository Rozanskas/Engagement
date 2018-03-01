package com.mindaugas.engagementapp.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

// Do not use @Service ,@Repository or @Component 
abstract public class BaseDao extends NamedParameterJdbcDaoSupport{

	@Autowired
	public void setDataSource2(DataSource ds){
		 super.setDataSource(ds);
	}
}

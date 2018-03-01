package com.mindaugas.engagementapp.independenttest;

import java.security.spec.DSAGenParameterSpec;

import javax.naming.Context;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mindaugas.engagementapp.config.SpringRootConfig;

public class TestDataSource {

	public static void main(String[] args){
		
	ApplicationContext context = new AnnotationConfigApplicationContext(SpringRootConfig.class);
    DataSource ds = context.getBean(DataSource.class);
    JdbcTemplate jt = new JdbcTemplate(ds);
    String sql = "INSERT INTO user(`name`, `phone`, `email`, `address`, `loginName`, `password`) VALUES(?,?,?,?,?,?)";
    Object[] param = new Object[]{"Mindaugas","132456","email","Address","lkkame","pass"};
    jt.update(sql,param);
    System.out.println("DB is working");
}
}

package com.gcu.data;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.AuthenticationModel;

@Service
public class FormBasedAuthenticationDataService implements AuthenticationDataAccessInterface<AuthenticationModel> {

	@Autowired javax.sql.DataSource dataSource;
	@Autowired JdbcTemplate jdbcTemplateObject;
	
	public FormBasedAuthenticationDataService(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean AuthenticateUser(AuthenticationModel authenticationModel) {
		
		String sql = "SELECT COUNT(USERID) as `COUNT` FROM USERS WHERE `USERID` = ? AND `PASSWORD` = ?";
		
		int numOfRows = 0;
		Object[] params = new Object[] {authenticationModel.getUsername(), authenticationModel.getPassword()};
		int[] dataTypes = new int[] {Types.NVARCHAR, Types.NVARCHAR};
			
		try {
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, params, dataTypes);
			if(srs.next()) {
				numOfRows = srs.getInt("COUNT");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(numOfRows == 1) {
			return true;
		} else {
			return false;
		}
	}

}

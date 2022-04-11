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
		
		String sql = "SELECT `PASSWORD` as `PASSWORD` FROM USERS WHERE `USERNAME` = ?";
		
		Object[] params = new Object[] {authenticationModel.getUsername()};
		int[] dataTypes = new int[] {Types.NVARCHAR};
		boolean passwordValid = false;
			
		try {
			String password = jdbcTemplateObject.queryForObject(sql, params, String.class);

			if(password.equals(authenticationModel.getPassword())) {
				passwordValid = true;
			}

			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return passwordValid;
	}

	@Override
	public boolean DoesAccountExist(AuthenticationModel authenticationModel) {
		String sql = "SELECT * FROM `USERS` WHERE `USERNAME` = ?";
		
		Object[] params = new Object[] {authenticationModel.getUsername()};
		int[] dataTypes = new int[] {Types.NVARCHAR};
		
		try {
			SqlRowSet srs = this.jdbcTemplateObject.queryForRowSet(sql, params, dataTypes);
			if(srs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}

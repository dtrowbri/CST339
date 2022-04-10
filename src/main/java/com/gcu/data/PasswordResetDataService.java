package com.gcu.data;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.model.ResetPasswordModel;

@Service
public class PasswordResetDataService implements PasswordResetInterface <ResetPasswordModel> {
	
	@Autowired javax.sql.DataSource dataSource;
	@Autowired JdbcTemplate jdbcTemplateObject;
	
	public PasswordResetDataService(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean ResetPassword(ResetPasswordModel resetPasswordModel) {
		String sql = "UPDATE `USERS` SET `PASSWORD` = ? WHERE `USERNAME` = ?";
		
		Object[] params = new Object[] {resetPasswordModel.getNewPassword(), resetPasswordModel.getUsername()};
		int[] dataTypes = new int[] {Types.NVARCHAR, Types.NVARCHAR};
		
		int numOfRows = 0;
		
		try {
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if(numOfRows == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
}

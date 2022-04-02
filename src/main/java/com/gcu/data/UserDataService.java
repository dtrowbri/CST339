package com.gcu.data;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.gcu.model.UserModel;

@Service
public class UserDataService implements DataAccessInterface<UserModel> {

	@Autowired javax.sql.DataSource dataSource;
	@Autowired JdbcTemplate jdbcTemplateObject;
	
	public UserDataService(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM USERS";
		List<UserModel> users = new ArrayList<UserModel>();
		
		try {
			SqlRowSet srs = this.jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
					users.add(new UserModel(srs.getInt("UserId"),
							srs.getNString("UserName"),
							srs.getNString("FirstName"),
							srs.getNString("LastName"),
							srs.getNString("Email"),
							srs.getNString("Address"),
							srs.getNString("Phone")));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return users;
	}

	@Override
	public UserModel findById(int id) {
		
		String sql = "SELECT * FROM USERS WHERE USERID = ?";
		
		UserModel user = new UserModel();
		
		try {
			SqlRowSet srs = this.jdbcTemplateObject.queryForRowSet(sql);
			user.setUserId(srs.getInt("UserId"));
			user.setPassword(srs.getNString("Password"));
			user.setUsername(srs.getNString("UserName"));
			user.setfName(srs.getNString("FirstName"));
			user.setlName(srs.getNString("LastName"));
			user.setEmail(srs.getNString("Address"));
			user.setPhone(srs.getNString("Phone"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean create(UserModel user) {
		
		String sql = "INSERT INTO USERS (`USERID`, `USERNAME`, `FIRSTNAME`, `LASTNAME`, `EMAIL`, `ADDRESS`, `PHONE`, `PASSWORD`) VALUES (null,?,?,?,?,?,?,?)";
		
		int numOfRows = 0;
		Object[] params = new Object[] {user.getUserId(), user.getUsername(), user.getfName(), user.getlName(), user.getAddress(), user.getPhone(), user.getPassword()};
		int[] dataTypes = new int[] {Types.INTEGER, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR};
		
		try {
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(numOfRows == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(UserModel user) {

		String sql = "UPDATE USERS `FIRSTNAME` = ?, `LASTNAME` = ?, `EMAIL` = ?, `ADDRESS` = ?, `PHONE` = ? WHERE `USERID` = ?";
		
		int numOfRows = 0;
		Object[] params = new Object[] {user.getfName(), user.getlName(), user.getEmail(), user.getAddress(), user.getPhone(), user.getUserId()};
		int[] dataTypes = new int[] {Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR, Types.INTEGER};
		
		try{
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(numOfRows == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(UserModel user) {
		
		String sql = "DELETE FROM USERS WHERE `USERID` = ?";
		
		int numOfRows = 0;
		Object[] params = new Object[] {user.getUserId()};
		int[] dataTypes = new int[] {Types.INTEGER};
		
		try {
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
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

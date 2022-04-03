package com.gcu.business;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;

public class AccountCreationService implements RegistrationBusinessServiceInterface {
	
	@Autowired 
	@Qualifier("UserDataAccess")
	DataAccessInterface service;
	
	public AccountCreationService() {
		
	}
	
	/**
	 * Compare the entered username to the database to make sure the name is available
	 * 
	 * Currently only compares against the "admin" username 
	 */
	@Override
	public boolean isUserNameTaken(@Valid UserModel userModel) {
		if (!userModel.getUsername().equals("admin")) {
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public boolean createUser(UserModel user) {
		if(service.create(user)) {
			return true;
		} else {
			return false;
		}
	}

}

package com.gcu.business;

import javax.validation.Valid;

import com.gcu.model.UserModel;

public class AccountCreationAuthenticationService implements RegistrationBusinessServiceInterface {
	
	public AccountCreationAuthenticationService() {
		
	}
	
	/**
	 * Compare the entered username to the database to make sure the name is available
	 * 
	 * Currently only compares against the "admin" username 
	 */
	@Override
	public boolean Authenticate(@Valid UserModel userModel) {
		if (!userModel.getUsername().equals("admin")) {
			return true;
		} 
		else {
			return false;
		}
	}

}

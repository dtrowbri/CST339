package com.gcu.business;

import javax.validation.Valid;
import com.gcu.model.AuthenticationModel;
import com.gcu.model.UserModel;

public class FormBasedAuthenticationService implements AuthenticationBusinessServiceInterface {
	
	public FormBasedAuthenticationService() {
		
	}
	
	@Override
	public boolean Authenticate(@Valid AuthenticationModel authenticationModel) {
		
		if(authenticationModel.getUsername().equals("admin") && authenticationModel.getPassword().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

}

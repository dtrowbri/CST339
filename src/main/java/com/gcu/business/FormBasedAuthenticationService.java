package com.gcu.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.AuthenticationModel;
import com.gcu.model.UserModel;
import com.gcu.data.AuthenticationDataAccessInterface;

public class FormBasedAuthenticationService implements AuthenticationBusinessServiceInterface {
	
	@Autowired AuthenticationDataAccessInterface service;
	@Autowired RegistrationBusinessServiceInterface regService;
	
	public FormBasedAuthenticationService() {
		
	}
	
	/*@Override
	public boolean Authenticate(@Valid AuthenticationModel authenticationModel) {
		
		if(authenticationModel.getUsername().equals("admin") && authenticationModel.getPassword().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}*/
	
	@Override
	public boolean Authenticate(@Valid AuthenticationModel authenticationModel) {
		if(service.DoesAccountExist(authenticationModel)) {
			return service.AuthenticateUser(authenticationModel);
		} else {
			return false;
		}
	}
	
}

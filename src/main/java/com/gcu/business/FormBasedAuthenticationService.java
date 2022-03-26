package com.gcu.business;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.gcu.model.AuthenticationModel;

public class FormBasedAuthenticationService implements AuthenticationBusinessServiceInterface {
	
	public FormBasedAuthenticationService() {
		
	}
	
	@Override
	public boolean Authenticate(@Valid AuthenticationModel authenticationModel, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return false;
		}
		
		return true;
	}
}

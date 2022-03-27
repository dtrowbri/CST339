package com.gcu.business;

import javax.validation.Valid;

import com.gcu.model.UserModel;

public interface RegistrationBusinessServiceInterface {
	
	public boolean Authenticate(@Valid UserModel userModel);

}

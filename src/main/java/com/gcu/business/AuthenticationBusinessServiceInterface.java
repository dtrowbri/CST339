package com.gcu.business;

import javax.validation.Valid;
import com.gcu.model.AuthenticationModel;

public interface AuthenticationBusinessServiceInterface {

	public boolean Authenticate(@Valid AuthenticationModel authenticationModel);
}

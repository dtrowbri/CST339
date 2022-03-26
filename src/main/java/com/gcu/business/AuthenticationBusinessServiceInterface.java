package com.gcu.business;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.gcu.model.AuthenticationModel;

public interface AuthenticationBusinessServiceInterface {

	public boolean Authenticate(@Valid AuthenticationModel authenticationModel, BindingResult bindingResult);
}

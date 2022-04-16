package com.gcu.business;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.gcu.model.UserModel;

@Service
public interface AccountUpdateServiceInterface {
	
	public boolean updateUser(@Valid UserModel user);

}

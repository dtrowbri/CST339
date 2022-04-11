package com.gcu.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.UserModel;

public class AccountUpdateService implements AccountUpdateServiceInterface {
	
	@Autowired private DataAccessInterface<UserModel> dai;

	@Override
	public boolean updateUser(@Valid UserModel user) {
		return dai.update(user);
		//return true;
	}

}

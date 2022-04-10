package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.data.PasswordResetInterface;
import com.gcu.model.ResetPasswordModel;

@Service
public class ResetPasswordService implements ResetPasswordServiceInterface {

	@Autowired PasswordResetInterface passwordService;
	
	@Override
	public boolean ResetPassword(ResetPasswordModel resetPassword) {
		return passwordService.ResetPassword(resetPassword);
		//return true;
	}

}

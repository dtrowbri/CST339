package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gcu.business.*;
import com.gcu.data.DataAccessInterface;
import com.gcu.data.UserDataService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.gcu.business.*;

@Configuration
@EnableEncryptableProperties
public class SpringConfig {
	
	@Bean("authenticationBusinessServiceInterface")
	public AuthenticationBusinessServiceInterface getAuthenticationService() {
		return new FormBasedAuthenticationService();
	}
	
	@Bean("registrationBusinessServiceInterface")
	public RegistrationBusinessServiceInterface getRegistrationService() {
		return new AccountCreationService();
	}
	
	@Bean("postServiceInterface")
	public PostServiceInterface getPostServer() {
		return new PostService();
	}
	
	@Bean("ResetPasswordInterface")
	public ResetPasswordServiceInterface getResetPasswordInterface() {
		return new ResetPasswordService();
	}
	
	@Bean("AccountUpdateServiceInterface")
	public AccountUpdateServiceInterface getAccountUpdateService() {
		return new AccountUpdateService();
	}
}

package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.AccountCreationAuthenticationService;
import com.gcu.business.AuthenticationBusinessServiceInterface;
import com.gcu.business.FormBasedAuthenticationService;
import com.gcu.business.RegistrationBusinessServiceInterface;

@Configuration
public class SpringConfig {
	
	@Bean("authenticationBusinessServiceInterface")
	public AuthenticationBusinessServiceInterface getAuthenticationService() {
		return new FormBasedAuthenticationService();
	}
	
	@Bean("registrationBusinessServiceInterface")
	public RegistrationBusinessServiceInterface getRegistrationService() {
		return new AccountCreationAuthenticationService();
	}
}

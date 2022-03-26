package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.AuthenticationBusinessServiceInterface;
import com.gcu.business.FormBasedAuthenticationService;

@Configuration
public class SpringConfig {
	
	@Bean("authenticationBusinessServiceInterface")
	public AuthenticationBusinessServiceInterface getAuthenticationService() {
		return new FormBasedAuthenticationService();
	}
}

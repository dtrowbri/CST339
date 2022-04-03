package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gcu.business.*;

@Configuration
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
}

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationModel {

	@NotNull(message="User name is a required field")
	@Size(min=1, max=32, message="User name must be between 1 and 32 characters")
    String username;
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	String password;

	public AuthenticationModel() {
		
	}
	
	public AuthenticationModel(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

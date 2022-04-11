package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordModel {

	@NotNull(message="User name is a required field")
	@Size(min=1, max=32, message="User name must be between 1 and 32 characters")
	private String Username;
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String CurrentPassword;
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String NewPassword;
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String VerifyNewPassword;
	
	public ResetPasswordModel() {}
	
	public ResetPasswordModel(String username, String password, String newPassword, String verifyNewPassword) {
		this.setUsername(username);
		this.setCurrentPassword(password);
		this.setNewPassword(newPassword);
		this.setVerifyNewPassword(verifyNewPassword);
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getCurrentPassword() {
		return CurrentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		CurrentPassword = currentPassword;
	}

	public String getNewPassword() {
		return NewPassword;
	}

	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}

	public String getVerifyNewPassword() {
		return VerifyNewPassword;
	}

	public void setVerifyNewPassword(String verifyNewPassword) {
		VerifyNewPassword = verifyNewPassword;
	}
	
	public boolean isNewPasswordMatch() {
		return this.getNewPassword().equals(this.getVerifyNewPassword());
	}
}


package com.gcu.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserModel {
	
	/*
	 * Size annotation is used to require the user add some form of input 
	 * (NotNull was not providing any error even if the input was left blank)
	 */
	
	private int UserId;
	
	@Size(min=1, message="This is a required field")
	private String username;
	
	@Size(min=1, message="This is a required field")
	private String password;
	
	@Size(min=1, message="This is a required field")
	private String fName;
	
	@Size(min=1, message="This is a required field")
	private String lName;
	
	@Size(min=1, message="This is a required field")
	@Pattern(regexp="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message="Invalid Email Address")
	private String email;
	
	@Size(min=1, message="This is a required field")
	private String address;
	
	@Size(min=10, max=10, message="Invalid Phone Number")
	private String phone;
	
	/**
	 * Default Constructor
	 */
	public UserModel() {	
	}
	
	/**
	 * UserModel Object Created from known parameters
	 * @param username
	 * @param password
	 * @param fName
	 * @param lName
	 * @param email
	 * @param address
	 * @param phone
	 */
	public UserModel(int UserId, String username, String fName, String lName, String email, String address, String phone) {
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	
	public UserModel(String username, String password, String fName, String lName, String email, String address, String phone) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	/*
	 * Get and Set methods for all User variables
	 */
	
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
}

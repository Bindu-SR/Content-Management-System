package com.example.cms.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserRequest {

	@NotNull(message = "Enter User name")
	private String userName;
	@Email
	private String email;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

package org.smal2.service.auth;

public class LoginUserRequest {

	private String registration;
	private String password;

	public LoginUserRequest() {
	}

	public LoginUserRequest(String registration, String password) {
		this.registration = registration;
		this.password = password;
	}

	public String getRegistration() {
		return registration;
	}

	public String getPassword() {
		return password;
	}
}

package org.smal2.service.user;

public class RegisterPrivilegedUserRequest {

	private String registration;
	private String name;
	private String password;
	private String email;
	private UserType type;

	public RegisterPrivilegedUserRequest() {
	}

	public RegisterPrivilegedUserRequest(String registration, String password,
			String name, String email, UserType type) {
		this.registration = registration;
		this.password = password;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public String getRegistration() {
		return registration;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public UserType getType() {
		return type;
	}
}

package org.smal2.service.user;

import java.util.Date;

public class RegisterPrivilegedUserRequest {

	private String registration;
	private String name;
	private String password;
	private Date birthDate;
	private UserType type;

	public RegisterPrivilegedUserRequest(String registration, String password,
			String name, Date birthDate, UserType type) {
		super();
		this.registration = registration;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public UserType getType() {
		return type;
	}
}

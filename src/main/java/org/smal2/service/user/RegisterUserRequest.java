package org.smal2.service.user;

import java.util.Date;

public class RegisterUserRequest {

	private String registration;
	private String name;
	private Date birthDate;
	private UserType type;

	public RegisterUserRequest(String registration, String name,
			Date birthDate, UserType type) {
		super();
		this.registration = registration;
		this.name = name;
		this.birthDate = birthDate;
		this.type = type;
	}

	public String getRegistration() {
		return registration;
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
